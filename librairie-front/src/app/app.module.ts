import {BrowserModule}                                 from '@angular/platform-browser';
import {NgModule}                                      from '@angular/core';
import {AppRoutingModule}                              from './app-routing.module';
import {AppComponent}                                  from './app.component';
import {UserService}                                   from "../service/user.service"
import {HttpClientModule}                              from "@angular/common/http";
import {LoginComponent}                                from './login/login.component'
import {AuthService}                                   from "../service/auth.service"
import {SigninComponent}                               from './signin/signin.component';
import {ConnectionComponent}                           from './connection/connection.component';
import {LibrairieComponent}                            from './librairie/librairie.component';
import {FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LibrairieService}                              from "../service/librairie.service"
import {ReservationService}                            from "../service/reservation.service";
import {AccountComponent}                              from './account/account.component';
import { BrowserAnimationsModule }                     from '@angular/platform-browser/animations'
import {MatTabsModule}                                 from "@angular/material/tabs";
import { SearchComponent } from './search/search.component';
import { ReservationComponent } from './reservation/reservation.component';
import { PanierComponent } from './panier/panier.component';
import { WaitingComponent } from './waiting/waiting.component'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SigninComponent,
    ConnectionComponent,
    LibrairieComponent,
    AccountComponent,
    SearchComponent,
    ReservationComponent,
    PanierComponent,
    WaitingComponent,
  ], imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatTabsModule
  ],
  providers: [
    AuthService,
    UserService,
    LibrairieService,
    ReservationService,
    FormBuilder,
    ReactiveFormsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
