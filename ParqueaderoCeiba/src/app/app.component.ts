import { Component } from '@angular/core';
import { Http, Response} from '@angular/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  //title = 'app';
  private apiUrl ='http://localhost:9091/consultarVehiculos/hX587f72'
  data: any = {};


  constructor(private http: Http){
    console.log("entra a la aplicación inicialmente");
  }

  getData() {
    return this.http.get(this.apiUrl);
      //.map((res: Response) => res.json);
  }

  vehiculo = {
    placa:"plaasa"
  }

  registrarVehiculo(){
    this.getData().subscribe(data => {
      console.log(data)
      console.log("lo que seaaaaaaaaaaa",this.vehiculo)
      this.data = data;
    })
  }
}
