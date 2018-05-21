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
  valorFacturado =0;

  registrarSalidaServices() {

    var url = 'http://localhost:9091/registrarSalida'

    const req = this.http.post(url, this.vehiculo)
    .subscribe(
      res => {
        this.valorFacturado = JSON.parse(res['_body']);
        alert("Monto a factuar es: $"+this.valorFacturado);
        this.vehiculo={};
      },
      err => {
        alert("Error tecnico Inesperado")
      }
    );
  }

  registrarSalida(){
    this.registrarSalidaServices();
  }

}
