import {Component, OnInit}      from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms"
import {LibrairieService}       from "../../service/librairie.service"
import {Search}                 from "../../models/search.model"
import {ReservationService}     from "../../service/reservation.service"
import {Livre}                  from "../../models/livre.model"

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  private searchForm: FormGroup
  private collection: [Livre] = null

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
      this.collection.forEach((livre: Livre) => {
        livre.quantite =
          this.reservationService.collection.filter(x => x.id == livre.id).length == livre.quantite ? 0 : livre.quantite
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
}
