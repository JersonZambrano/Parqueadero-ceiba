import { Component } from '@angular/core';
import { Http, Response} from '@angular/http';
import {map} from 'rxjs/operators';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor( private http: Http ) {
    console.log("entra a la aplicación inicialmente");
  }

  mostrarModal(){
  }

  trm = {};


}
