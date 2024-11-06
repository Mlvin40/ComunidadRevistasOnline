import { Component } from '@angular/core';
import { UtilComponent } from "../../util/util.component";
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-suscriptor-home',
  standalone: true,
  imports: [UtilComponent, RouterModule, CommonModule ],
  templateUrl: './suscriptor-home.component.html',
  styleUrl: './suscriptor-home.component.css'
})
export class SuscriptorHomeComponent {

}
