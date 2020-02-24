import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http"
import {Livre}      from "../models/livre.model"
import {Subject}    from "rxjs"

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  public count            = new Subject<number>();
  private collection: any = []

  constructor(private HttpClient: HttpClient) {
  }

  addToPanier = (livre: Livre) => {
    this.collection.push(livre)
    this.count.next(this.collection.length)
  }

  getPanier = () => {
    this.count.next(this.collection.length)
    return this.collection
  }

  deleteToPanier = (livre: Livre) => {
    this.collection.pop(livre)
    this.count.next(this.collection.length)
  }
}
