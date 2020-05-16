import {Component, OnInit} from '@angular/core';
import {WaitingModel} from "../../models/waiting.model"
import {Livre} from "../../models/livre.model"
import {ReservationService} from "../../service/reservation.service"
import {LibrairieService} from "../../service/librairie.service"
import {Router} from "@angular/router"

@Component({
  selector   : 'app-waiting',
  templateUrl: './waiting.component.html',
  styleUrls  : ['./waiting.component.scss']
})
export class WaitingComponent implements OnInit {
  empty: boolean
  error: boolean
  waiting: [WaitingModel]
  collection: Livre
  success: boolean = false

  constructor(private reservationService: ReservationService, private librairieService: LibrairieService, private router: Router) {
  }

  ngOnInit() {
    this.init()
  }

  private init() {
    this.error = true
    this.empty = true
    this.reservationService.getAllWaitByUserId().subscribe((resp: [WaitingModel]) => {
      resp.forEach(x => {
        this.librairieService.findById(String(x.livreId)).subscribe((resp: Livre) => {
          x.livreId = resp.nom
        })
        resp.forEach(x => {
          this.reservationService.getPosition(x.id).subscribe((resp: any) => {
            x.position = resp
          }, error => {
            console.log(error)
          })
        })
      })
      this.waiting = resp
    }, error => {
      console.log(error)
    })
  }

  getPosition(position: string) {
    if (position === undefined)
      return
    return position.toString().replace(".", " / ")
  }

  delete(id: number) {
    this.reservationService.deleteWait(id).subscribe(() => {
      this.success = true;
    }, err => {
      this.error = true
    });
    this.ngOnInit()
  }

  getDateRetour(dateNotification: Date) {
    return this.reservationService.addDays(dateNotification, 2).toLocaleDateString()
  }
}
