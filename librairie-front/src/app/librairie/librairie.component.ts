import {Component, OnInit} from '@angular/core';
import {LibrairieService} from "../../service/librairie.service"
import {Livre} from "../../models/livre.model"
import {ReservationService} from "../../service/reservation.service"
import {WaitingModel} from "../../models/waiting.model"

@Component({
  selector   : 'app-librairie',
  templateUrl: './librairie.component.html',
  styleUrls  : ['./librairie.component.scss']
})
export class LibrairieComponent implements OnInit {
  private collection: [Livre]
  private backup          = []
  private waitingList     = []
  private error: boolean  = false
  private message: string = " Une erreur est survenue, veuillez réessayer plus tard !"

  constructor(private librairieService: LibrairieService, private reservationService: ReservationService) {
  }

  ngOnInit() {
    this.init()
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

  isAllowed(i: number) {
    return this.backup[i]
  }

  getDate(id: number) {
    let dateRetour = null
    if (this.waitingList[id]) {
      if (this.waitingList[id][0]) {
        dateRetour = this.reservationService.addDays(this.waitingList[id][0].dateNotification, 2)
      }
    }
    return dateRetour === null ? "Aucune date de retour prévu" : dateRetour.toLocaleDateString();
  }

  private init() {
    this.librairieService.findAll().subscribe((response: [Livre]) => {
      this.collection = response
      this.collection.forEach((livre: Livre, index) => {
        livre.quantite     =
          this.reservationService.collection.filter(x => x.id == livre.id).length == livre.quantite ? 0 : livre.quantite
        this.backup[index] = livre.quantite === 0
        this.reservationService.getAllWaitByLivreId(livre.id).subscribe((resp: [WaitingModel]) => {
          this.waitingList[livre.id] = resp[0] !== undefined ? resp : []
        }, error => {
          console.log("error" + error)
        })
      })
    }, () => {
      this.error = true
    })
  }

  private addToPanier(livre: Livre) {
    let quantity = this.reservationService.collection.filter(x => x.id == livre.id).length
    if (quantity == livre.quantite && livre.quantite == 0) {
      livre.quantite = 0
    } else {
      livre.quantite--
      this.reservationService.addToPanier(livre)
    }
  }

  getNombrePersonne(id: number) {
    if (this.waitingList[id])
      return this.waitingList[id].length
    return 0;
  }
}
