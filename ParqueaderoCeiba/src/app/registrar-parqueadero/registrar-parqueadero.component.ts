import { Component, OnInit } from '@angular/core';
import { Http, Response} from '@angular/http';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-registrar-parqueadero',
  templateUrl: './registrar-parqueadero.component.html',
  styleUrls: ['./registrar-parqueadero.component.css']
})
export class RegistrarParqueaderoComponent implements OnInit {

    //title = 'app';
  private apiUrl ='http://localhost:9091/consultarVehiculos/hX587f75'
  data: any = {};
  constructor( private http: Http) { }

  ngOnInit() {
  }
  
  getData(){
  this.http.get(this.apiUrl);
    //.map((res: Response) => res.json);
  }

  registrarVehiculoServices(){
    console.log("entra quiiii")
    var url='http://localhost:9091/registrarIngreso'
    return this.http.post(url,this.vehiculo);//.pipe(map(res: => res.json())).subscribe(data => { console.log("exito")});
  }

  vehiculo = {
    placa:"plaasa"
  }

  registrarVehiculo(){
    /*this.getData().subscribe(data => {
      console.log(data)
      console.log("lo que seaaaaaaaaaaa",this.vehiculo)
      this.data = data;
    })*/

    this.registrarVehiculoServices().subscribe(data => {
      console.log(data)
      console.log("lo que seaaaaaaaaaaa",this.vehiculo)
      this.data = data;
    });
  }
}
