import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service"
import {Router} from "@angular/router"
import {ReservationService} from "../../service/reservation.service"
import {Subscription} from "rxjs"

@Component({
  selector   : 'app-account',
  templateUrl: './account.component.html',
  styleUrls  : ['./account.component.scss']
})
export class AccountComponent implements OnInit, OnDestroy {
  links: any[]
  activeLinkIndex = -1
  private countSubscription: Subscription;

  constructor(private router: Router, private userService: UserService, private reservationService: ReservationService) {
  }

  ngOnInit() {
    this.countSubscription = this.reservationService.count.subscribe(resp => {
      this.init(resp)
    })
    this.userService.isConnected()
    this.router.events.subscribe((res) => {
      this.activeLinkIndex = this.links.indexOf(this.links.find(tab => tab.link === this.router.url));
    });
    this.reservationService.getPanier()
  }

  ngOnDestroy() {
    this.countSubscription.unsubscribe()
  }

  private init(number: number) {
    this.links = [
      {
        label: 'Liste des livres disponibles',
        link : '/librairie',
        index: 0
      }, {
        label: 'Faire une recherche',
        link : '/search',
        index: 1
      }, {
        label: 'mes reservations en cours',
        link : '/reservation',
        index: 2
      }, {
        label: 'Ma liste d\'attente',
        link : '/waiting',
        index: 3
      }, {
        label: `Mon panier ${number}`,
        link : '/panier',
        index: 4
      }
    ]
  }

}
