import { Component } from '@angular/core';
import { UtilComponent } from "../../util/util.component";
import { Router, RouterModule } from '@angular/router';
import { UsuariosService } from '../../../services/usuarios/usuarios.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-anunciante-home',
  standalone: true,
  imports: [RouterModule, CommonModule, UtilComponent],
  templateUrl: './anunciante-home.component.html',
  styleUrl: './anunciante-home.component.css'
})
export class AnuncianteHomeComponent {
  
    constructor(private router: Router, private usuarioService: UsuariosService){
    if(!this.usuarioService.permisosAnunciante()){
      //redirigir al login
      this.router.navigate(['/login']);
    }
   }
}
