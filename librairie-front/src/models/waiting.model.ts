export class WaitingModel {
  constructor(public id: number,
              public livreId: number,
              public userId: number,
              public dateReservation: Date,
              public dateNotification: Date,
              public finished: boolean,
              public progress: boolean
  ) {
  }
}
