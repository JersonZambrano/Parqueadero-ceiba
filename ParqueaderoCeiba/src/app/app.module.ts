import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AlertModule } from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
//import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { RegistrarParqueaderoComponent } from './registrar-parqueadero/registrar-parqueadero.component';
import { ConsultarVehiculosComponent } from './consultar-vehiculos/consultar-vehiculos.component';
import { RegistrarSalidaComponent } from './registrar-salida/registrar-salida.component';
import { AlertModalComponent } from './alert-modal/alert-modal.component';

const appRoutes: Routes = [
  { path: 'registrarVehiculo', component: RegistrarParqueaderoComponent},
  { path: 'registrarSalida/:placa', component: RegistrarSalidaComponent},
  { path: 'consultarVehiculo', component: ConsultarVehiculosComponent}
];
@NgModule({
  declarations: [
    AppComponent,
    RegistrarParqueaderoComponent,
    ConsultarVehiculosComponent,
    RegistrarSalidaComponent,
    AlertModalComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    ),
    BrowserModule,
    AlertModule.forRoot(),
    HttpModule,
    FormsModule,
    //    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
