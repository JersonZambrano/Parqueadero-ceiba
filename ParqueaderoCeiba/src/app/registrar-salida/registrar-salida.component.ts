import { Component, OnInit } from '@angular/core';
import { Http, Response} from '@angular/http';
import {map} from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-registrar-salida',
  templateUrl: './registrar-salida.component.html',
  styleUrls: ['./registrar-salida.component.css']
})
export class RegistrarSalidaComponent implements OnInit {


  vehiculo = {
  }
  constructor( private http: Http, private _route: ActivatedRoute) { 
    console.log(this._route.snapshot.paramMap.get('placa'));
    if(this._route.snapshot.paramMap.get('placa') != null && this._route.snapshot.paramMap.get('placa') != ''){
      this.vehiculo={
        'placa':this._route.snapshot.paramMap.get('placa')
      }
    }
  }

  ngOnInit() {
  }

  mensaje=null;
  tipoMensaje=null;
  hayResultado =false;
  mostrarMensaje(textoMensaje,tipo){
    this.mensaje=textoMensaje;
    this.tipoMensaje=tipo;
    setTimeout(() => {
      this.mensaje= null;
    }, 100000);
  }

  registrarSalidaServices() {
    this.mensaje= null;
    var url = 'http://localhost:9091/registrarSalida'
    const req = this.http.post(url, this.vehiculo)
    .subscribe(
      res => {
        if(res['_body'] != null && res['_body'] != ""){
          this.hayResultado = true;
          this.vehiculo = JSON.parse(res['_body']);
        }else{          
          this.mostrarMensaje("El vehículo no se encuentra registrado actualmente en el parqueadero",'info');
        }
      },
      err => {        
        this.mostrarMensaje("Error técnico inesperado",'warn');
      }
    );
  }

  aceptarResultado(){
    this.hayResultado = false;
    this.vehiculo= {};
  }

  registrarSalida(){
    this.registrarSalidaServices();
  }
}
