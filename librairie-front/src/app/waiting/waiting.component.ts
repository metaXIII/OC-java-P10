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
    this.reservationService.getAllWaitByUserId().subscribe((resp: [WaitingModel]) => {
      resp.forEach(x => {
        this.librairieService.findById(String(x.livreId)).subscribe((res: Livre) => {
          x.livreId = res.nom
        })
        resp.forEach(y => {
          this.reservationService.getPosition(x.id).subscribe((response: any) => {
            y.position = response
          }, error => {
            console.log(error)
            this.error = true
          })
        })
      })
      this.waiting = resp
    }, error => {
      this.error = true
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
