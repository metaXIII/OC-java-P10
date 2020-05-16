import {Component, OnInit}  from '@angular/core';
import {ReservationService} from "../../service/reservation.service"
import {LibrairieService}   from "../../service/librairie.service"
import {Reservation}        from "../../models/reservation.model"

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent implements OnInit {
  reservations: any        = []
  empty: boolean           = false
  collection: any
  private error: boolean   = false
  private success: boolean = false

  constructor(private reservationService: ReservationService, private librairieService: LibrairieService) {
  }

  ngOnInit() {
    this.reservationService.getAllReservation().subscribe((resp: [Reservation]) => {
      this.collection = new Array(resp.length)
      resp.forEach((reservation, indexReservation) => {
        this.reservations.push(reservation)
        let el = reservation.livreId.split(',')
        el.forEach((id, indexLivre) => {
          this.collection[indexReservation] = new Array(indexLivre)
          this.librairieService.findById(id).subscribe((response) => {
            this.collection[indexReservation][indexLivre] = [response]
          }, (error) => {
            console.error(error)
          })
        })
      })
    }, error => {
      this.empty = true
    })
  }

  extend(reservation: Reservation) {
    this.reservationService.extend(reservation).subscribe(() => {
      this.success           = true
      reservation.dateLimite = this.reservationService.addDays(reservation.dateLimite, 28)
      reservation.extended   = true
    }, () => {
      this.error = true
    })
  }


}
