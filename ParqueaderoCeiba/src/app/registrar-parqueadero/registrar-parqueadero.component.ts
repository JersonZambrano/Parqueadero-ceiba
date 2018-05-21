import { Component, OnInit } from '@angular/core';
import { Http, Response} from '@angular/http';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-registrar-parqueadero',
  templateUrl: './registrar-parqueadero.component.html',
  styleUrls: ['./registrar-parqueadero.component.css']
})
export class RegistrarParqueaderoComponent implements OnInit {

  data: any = {};
  constructor( private http: Http) { }

  ngOnInit() {
  }
  
  tipoVehiculo = ['CARRO','MOTO'];
  //tipoVehiculo : ["CARRO","MOTO"]

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
