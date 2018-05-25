import { Component, OnInit , Input} from '@angular/core';

@Component({
  selector: 'app-alert-modal',
  templateUrl: './alert-modal.component.html',
  styleUrls: ['./alert-modal.component.css']
})
export class AlertModalComponent implements OnInit {

  @Input() mensaje: string;
  @Input() tipoMensaje: string;
  constructor() { }

  ngOnInit() {
  }

}
