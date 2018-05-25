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
  mensaje=null;
  tipoMensaje=null;
  mostrarMensaje(textoMensaje,tipo){
    this.mensaje=textoMensaje;
    this.tipoMensaje=tipo;
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
          this.mostrarMensaje("El vehículo no puede ingresar estos días",'info');
        }else if(validaciones['VALIDACION_DISPONIBILIDAD']  == false){     
          this.mostrarMensaje("No hay disponibilidad en el parqueadero en estos momentos",'info');
        }else if(validaciones['VALIDACION_YA_REGISTRADO']  == false){    
          this.mostrarMensaje("El vehículo ya se encuentra en el parqueadero",'info');
        }else{    
          this.mostrarMensaje("El vehículo fue registrado con éxito",'ok');
        }
        this.vehiculo={};
      },
      err => {  
        this.mostrarMensaje("Error técnico inesperado",'warn');
      }
    );
  }

  registrarVehiculo(){
    this.registrarVehiculoServices();
  }
}
