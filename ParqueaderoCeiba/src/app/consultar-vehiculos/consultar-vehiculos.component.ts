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

  mensaje=null;
  tipoMensaje=null;
  vehiculo = {
    placa:""
  }
  mostrarMensaje(textoMensaje,tipo){
    this.mensaje=textoMensaje;
    this.tipoMensaje=tipo;
    setTimeout(() => {
      this.mensaje= null;
      
    }, 3000);
  }

  listVehiculos = [];

  consultarVehiculoServices() {
    this.mensaje= null;
    var url = 'http://localhost:9091/consultarVehiculos/{placa}'
    url= url.replace('{placa}',this.vehiculo.placa);
    const req = this.http.get(url)
    .subscribe(
      res => {
        if(res['_body'] != null && res['_body'] != ""){
          this.listVehiculos = [JSON.parse(res['_body'])];
        }else{
          //this.listVehiculos=[];
          this.mostrarMensaje("El vehículo no se encuentra registrado en el parqueadero actualmente",'info');
        }
      },
      err => {
        let error = JSON.parse(err['_body']);
        this.mostrarMensaje("Error técnico inesperado: "+error.message,'warn');
      }
    );
  }

  consultarVehiculosServices() {
    this.vehiculo.placa=null;
    var url = 'http://localhost:9091/consultarRegistros'
    const req = this.http.get(url)
    .subscribe(
      res => {
        this.listVehiculos = JSON.parse(res['_body']);
      },
      err => {
        let error = JSON.parse(err['_body']);
        this.mostrarMensaje("Error técnico inesperado: "+error.message,'warn');
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
