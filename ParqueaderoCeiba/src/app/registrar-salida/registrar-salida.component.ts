import { Component, OnInit } from '@angular/core';
import { Http, Response} from '@angular/http';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-registrar-salida',
  templateUrl: './registrar-salida.component.html',
  styleUrls: ['./registrar-salida.component.css']
})
export class RegistrarSalidaComponent implements OnInit {

  constructor( private http: Http) { }

  ngOnInit() {
  }


  vehiculo = {
  }
  hayResultado =false;

  registrarSalidaServices() {

    var url = 'http://localhost:9091/registrarSalida'

    const req = this.http.post(url, this.vehiculo)
    .subscribe(
      res => {
        if(res['_body'] != null && res['_body'] != ""){
          this.hayResultado = true;
          this.vehiculo = JSON.parse(res['_body']);
          //alert("Monto a factuar es: $"+this.valorFacturado);
          //this.vehiculo={};
        }else{
          alert("El vehiculo no se encuentra registrado actualmente en el parqueadero");
        }
      },
      err => {
        alert("Error tecnico Inesperado")
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
