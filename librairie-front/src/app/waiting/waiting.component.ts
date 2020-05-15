import { Component, OnInit } from '@angular/core';
import {WaitingModel} from "../../models/waiting.model"
import {Livre} from "../../models/livre.model"
import {ReservationService} from "../../service/reservation.service"
import {LibrairieService} from "../../service/librairie.service"

@Component({
  selector: 'app-waiting',
  templateUrl: './waiting.component.html',
  styleUrls: ['./waiting.component.scss']
})
export class WaitingComponent implements OnInit {
  empty: boolean
  error: boolean
  waiting: [WaitingModel]
  collection: Livre

  constructor(private reservationService: ReservationService, private librairieService: LibrairieService) {}

  ngOnInit() {
    this.init()
  }

  private init() {
    this.error = true
    this.empty = true
    this.reservationService.getAllWaitByUserId().subscribe((resp: [WaitingModel]) => {
      console.log(resp)
    }, error => {
      console.log(error)
    })
  }
}
