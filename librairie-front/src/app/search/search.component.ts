import {Component, OnInit}      from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms"
import {LibrairieService}       from "../../service/librairie.service"
import {Search}                 from "../../models/search.model"
import {ReservationService}     from "../../service/reservation.service"
import {Livre}                  from "../../models/livre.model"
import {WaitingModel} from "../../models/waiting.model"

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  private searchForm: FormGroup
  private collection: [Livre] = null
  private backup          = []
  private waitingList     = []
  private error: boolean  = false
  private message: string = " Une erreur est survenue, veuillez réessayer plus tard !"

  constructor(private formBuilder: FormBuilder, private librairieService: LibrairieService, private reservationService: ReservationService) {
  }

  ngOnInit() {
    this.initForm()
  }

  Submit() {
    let form   = this.searchForm.value
    let search = new Search(form['nom'], form['auteur'], form['categorie'])
    this.librairieService.findByFields(search).subscribe((response: [Livre]) => {
      this.collection = response
      this.collection.forEach((livre: Livre, index) => {
        livre.quantite =
          this.reservationService.collection.filter(x => x.id == livre.id).length == livre.quantite ? 0 : livre.quantite
        this.backup[index] = livre.quantite === 0
        this.reservationService.getAllWaitByLivreId(livre.id).subscribe((resp: [WaitingModel]) => {
          this.waitingList[livre.id] = resp[0] !== undefined ? resp : []
        }, error => {
          console.log("error" + error)
        })
      })
      console.log(response)
    }, error => {
      console.log(error)
    })
  }

  addToPanier(livre: Livre) {
    let quantity = this.reservationService.collection.filter(x => x.id == livre.id).length
    if (quantity == livre.quantite && livre.quantite == 0) {
      livre.quantite = 0
    } else {
      livre.quantite--
      this.reservationService.addToPanier(livre)
    }
  }

  private initForm() {
    this.searchForm = this.formBuilder.group({
      nom: [''],
      auteur: [''],
      categorie: [''],
    })
  }

  isAllowed(i: number) {
    return this.backup[i]
  }

  addToWaiting(livre: Livre) {
    this.reservationService.addToWaiting(livre).subscribe((resp: any) => {
      if (resp.statusCodeValue !== 409)
        console.log("ok")
      else {
        this.error   = true
        this.message = "Vous êtes déjà sur liste d'attente pour ce livre"
      }
    }, error => {
      this.error = true
    });
  }

  getDate(id: number) {
    let dateRetour = null
    if (this.waitingList[id]) {
      if (this.waitingList[id][0]) {
        dateRetour = this.reservationService.addDays(this.waitingList[id][0].dateNotification, 2)
        console.log(dateRetour)
      }
    }
    return dateRetour === null ? "Aucune date de retour prévu" : dateRetour.toLocaleDateString();
  }
}
