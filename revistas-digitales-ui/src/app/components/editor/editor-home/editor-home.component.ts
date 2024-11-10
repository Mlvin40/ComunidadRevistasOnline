import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UtilComponent } from "../../util/util.component";
import { UsuariosService } from '../../../services/usuarios/usuarios.service';
import { AnuncioIndividualComponent } from "../../anunciante/anuncio-individual/anuncio-individual.component";


@Component({
  selector: 'app-editor-home',
  standalone: true,
  imports: [RouterModule, CommonModule, UtilComponent, AnuncioIndividualComponent],
  templateUrl: './editor-home.component.html',
  styleUrl: './editor-home.component.css'
})

export class EditorHomeComponent implements OnInit {

  rutaActual!: string;
  constructor(private router: Router, private usuarioService: UsuariosService) {
  }

  ngOnInit(): void {
    this.rutaActual = this.router.url;
  }

}
