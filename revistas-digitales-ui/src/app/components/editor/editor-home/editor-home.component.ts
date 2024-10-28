import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UtilComponent } from "../../util/util.component";
import { UsuariosService } from '../../../services/usuarios/usuarios.service';

@Component({
  selector: 'app-editor-home',
  standalone: true,
  imports: [RouterModule, CommonModule, UtilComponent],
  templateUrl: './editor-home.component.html',
  styleUrl: './editor-home.component.css'
})

export class EditorHomeComponent {
  
  //obtener el token del usuario y verificar si existe
  constructor(private router: Router, private usuarioService: UsuariosService){
    if(!this.usuarioService.permisosEditor()){
      //redirigir al login
      this.router.navigate(['/login']);
    }
   }
}
