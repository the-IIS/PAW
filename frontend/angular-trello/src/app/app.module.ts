import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CardListComponent } from './components/card-list/card-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {NgxWebstorageModule} from 'ngx-webstorage';
import {HttpClientInterceptor} from './http-client-interceptor';
import { CardService } from './services/card.service';
import { LoginComponent } from './components/login/login.component';
import { TableListComponent } from './components/table-list/table-list.component';
import {AuthGuard} from './auth.guard';


@NgModule({
  declarations: [
    AppComponent,
    CardListComponent,
    LoginComponent,
    TableListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxWebstorageModule.forRoot(),
    RouterModule.forRoot([
      {path: 'tableList', component: TableListComponent, canActivate: [AuthGuard]},
      {path: 'login', component: LoginComponent}
    ]),
    HttpClientModule
  ],
  providers: [CardService, {provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
