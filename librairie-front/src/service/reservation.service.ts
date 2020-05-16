import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Livre} from '../models/livre.model';
import {Subject} from 'rxjs';
import {UserService} from './user.service';
import {Reservation} from '../models/reservation.model';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  public count           = new Subject<number>();
  public collection: any = [];

  constructor(private httpClient: HttpClient, private userService: UserService) {
  }

  calculDate = (reservation: Reservation) => {
    const date1 = new Date();
    const date2 = new Date(reservation.dateLimite);
    return Math.ceil((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
  }

  addToPanier = (livre: Livre) => {
    this.collection.push(livre);
    this.count.next(this.collection.length);
  }

  getPanier = () => {
    this.count.next(this.collection.length);
    return this.collection;
  }

  deleteToPanier = (livre: Livre) => {
    this.collection.pop(livre);
    this.count.next(this.collection.length);
  }

  deleteAllToPanier = () => {
    this.collection = [];
  }

  reserve = (collection: [Livre]) => {
    const data: any = {
      collection,
      user: this.userService.getUser()
    };
    return this.httpClient.post('/service/reservation/reserve', data);
  }

  getAllReservation = () => {
    return this.httpClient.post('/service/reservation/reservations', this.userService.getUser());
  }

  extend = (reservation: Reservation) => {
    return this.httpClient.put('/service/reservation/extend', reservation);
  }

  getAllWaitByLivreId = (id: number) => {
    return this.httpClient.get('/service/reservation/getWaitForLivreId/' + id)
  }

  addToWaiting = (livre: Livre) => {
    const data: any = {
      livreId: livre.id,
      user   : this.userService.getUser()
    }
    return this.httpClient.post('/service/reservation/waiting', data);
  }

  addDays(date, days) {
    let result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
  }

  getAllWaitByUserId = () => {
    return this.httpClient.post("/service/reservation/getAllWaitForUser", this.userService.getUser())
  }

  getPosition = (livreId: number) => {
    return this.httpClient.get("/service/reservation/position/" + livreId)
  }

  deleteWait = (id: number) => {
    return this.httpClient.put("/service/reservation/deleteById", id);
  }
}
