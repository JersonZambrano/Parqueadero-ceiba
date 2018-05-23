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
  mensaje=null;
  mostrarMensaje(textoMensaje){
    this.mensaje=textoMensaje;
    setTimeout(() => {
      this.mensaje= null;
    }, 3000);
  }

  vehiculo = {
  }

  registrarVehiculoServices() {
    this.mensaje= null;
    var url = 'http://localhost:9091/registrarIngreso'
    this.http.post(url, this.vehiculo)
      .subscribe(
      res => {
        console.log(res);
        var validaciones = JSON.parse(res['_body']);
        if(validaciones['VALIDACION_DOMINGO_LUNES'] == false){    
          this.mostrarMensaje("El vehiculo no puede ingresar estos dias");
        }else if(validaciones['VALIDACION_DISPONIBILIDAD']  == false){     
          this.mostrarMensaje("No hay disponibilidad en el parqueadero en estos momentos");
        }else if(validaciones['VALIDACION_YA_REGISTRADO']  == false){    
          this.mostrarMensaje("El vehiculo ya se encuentra en el parqueadero");
        }else{    
          this.mostrarMensaje("El vehiculo fue registrado con exito");
        }
        this.vehiculo={};
      },
      err => {  
        this.mostrarMensaje("Error tecnico Inesperado");
      }
    );
  }

  registrarVehiculo(){
    this.registrarVehiculoServices();
  }
}
