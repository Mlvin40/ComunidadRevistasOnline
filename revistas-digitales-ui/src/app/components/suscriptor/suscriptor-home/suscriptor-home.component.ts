import { Component, OnInit } from '@angular/core';
import { UtilComponent } from "../../util/util.component";
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AnuncioIndividualComponent } from "../../anunciante/anuncio-individual/anuncio-individual.component";

@Component({
  selector: 'app-suscriptor-home',
  standalone: true,
  imports: [UtilComponent, RouterModule, CommonModule, AnuncioIndividualComponent],
  templateUrl: './suscriptor-home.component.html',
  styleUrl: './suscriptor-home.component.css'
})
export class SuscriptorHomeComponent implements OnInit {

  rutaActual!: string;
  
  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.rutaActual = this.router.url;
  }

}
