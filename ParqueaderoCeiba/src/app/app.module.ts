import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AlertModule } from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
//import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { RegistrarParqueaderoComponent } from './registrar-parqueadero/registrar-parqueadero.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrarParqueaderoComponent
  ],
  imports: [
    BrowserModule,
    AlertModule.forRoot(),
    HttpModule,
    FormsModule//,
//    NgbModule
  ],
  providers: [AppComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
