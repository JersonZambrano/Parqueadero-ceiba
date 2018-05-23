import { Component, OnInit } from '@angular/core';
import { Http, Response} from '@angular/http';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-consultar-vehiculos',
  templateUrl: './consultar-vehiculos.component.html',
  styleUrls: ['./consultar-vehiculos.component.css']
})
export class ConsultarVehiculosComponent implements OnInit {

  constructor(private http: Http) { 
    this.consultarVehiculosServices();
  }

  ngOnInit() {
  }

  vehiculo = {
    placa:""
  }

  listVehiculos = [];

  consultarVehiculoServices() {

    var url = 'http://localhost:9091/consultarVehiculos/{placa}'
    url= url.replace('{placa}',this.vehiculo.placa);
    const req = this.http.get(url)
    .subscribe(
      res => {
        if(res['_body'] != null && res['_body'] != ""){
          this.listVehiculos = [JSON.parse(res['_body'])];
        }else{
          this.listVehiculos=[];
          alert("El vehiculo no se encuentra registrado en el parqueadero actualmente")
        }
      },
      err => {
        let error = JSON.parse(err['_body']);
        alert("Error tecnico Inesperado: "+error.message)
      }
    );
  }

  consultarVehiculosServices() {

    var url = 'http://localhost:9091/consultarRegistros'
    url= url.replace('{placa}',this.vehiculo.placa);
    const req = this.http.get(url)
    .subscribe(
      res => {
        this.listVehiculos = JSON.parse(res['_body']);
      },
      err => {
        alert("Error tecnico Inesperado")
      }
    );
  }

  consultarVehiculo(){
    if(this.vehiculo.placa!= null && this.vehiculo.placa != ""){
      this.consultarVehiculoServices();
    }else{
      this.consultarVehiculosServices();
    }
  }
}
