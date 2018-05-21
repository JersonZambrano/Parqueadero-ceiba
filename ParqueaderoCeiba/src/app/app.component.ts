import { Component } from '@angular/core';
import { Http, Response} from '@angular/http';
import {map} from 'rxjs/operators';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  //title = 'app';
  private apiUrl ='http://localhost:9091/consultarVehiculos/hX587f75'
  data: any = {};


  constructor( private http: Http ) {
    console.log("entra a la aplicación inicialmente");
  }

  tipoVehiculo = ['CARRO','MOTO'];
  //tipoVehiculo : ["CARRO","MOTO"]

  getData() {
    this.http.get(this.apiUrl);
    //.map((res: Response) => res.json);
  }
  vehiculo = {
  }

  registrarVehiculoServices() {
    //console.log("entra quiiii")
    var url = 'http://localhost:9091/registrarIngreso'
    //return this.http.post(url,this.vehiculo);//.pipe(map(res: => res.json())).subscribe(data => { console.log("exito")});

    const req = this.http.post(url, this.vehiculo)
    .subscribe(
      res => {
        console.log(res);
        var validaciones = JSON.parse(res['_body']);
        console.log(validaciones);
        if(validaciones['VALIDACION_DOMINGO_LUNES'] == false){
          console.log("El vehiculo no puede ingresar estos dias");
          alert("El vehiculo no puede ingresar estos dias")
        }else if(validaciones['VALIDACION_DISPONIBILIDAD']  == false){
          console.log("No hay disponibilidad en el parqueadero en estos momentos");
          alert("No hay disponibilidad en el parqueadero en estos momentos")
        }else if(validaciones['VALIDACION_YA_REGISTRADO']  == false){
          alert("El vehiculo ya se encuentra en el parqueadero")
        }else{
          alert("El vehiculo fue registrado con exito")
        }
        this.vehiculo={};
      },
      err => {
        alert("Error tecnico Inesperado")
        console.log("Error occured");
      }
    );
  }

  registrarVehiculo(){
    this.registrarVehiculoServices();
  }
}
