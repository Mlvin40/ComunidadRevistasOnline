import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { ConfiguracionUsuarioService } from '../../../services/configuracion-usuario/configuracion-usuario.service';
import { Usuario } from '../../../entidades/Usuario';
import { TipoUsuario } from '../../../entidades/TipoUsuario';

@Component({
  selector: 'app-perfil-usuario',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css'],
})
export class PerfilUsuarioComponent implements OnInit {

  usuario: Usuario | null = null;
  texto: string = '';
  fotoPerfilUrl: string | null = null;
  rolTexto: string = '';

  constructor(
    private route: ActivatedRoute,
    private configuracionUsuarioService: ConfiguracionUsuarioService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    // Obtén el nombre de usuario de los parámetros de la ruta
    const nombreUsuario = this.route.snapshot.paramMap.get('nombreUsuario');

    if (nombreUsuario) {
      this.configuracionUsuarioService.obtenerDatosUsuario(nombreUsuario).subscribe(
        (datosUsuario: Usuario) => {
          this.usuario = datosUsuario;
          this.texto = datosUsuario.texto;
          this.rolTexto = TipoUsuario[datosUsuario.rol];

          // Convertir imagen binaria a Base64 para mostrarla
          if (datosUsuario.fotoPerfil) {
            this.fotoPerfilUrl = `data:image/png;base64,${datosUsuario.fotoPerfil}`;
          }
        },
        (error) => {
          console.error('Error al obtener datos del usuario', error);
        }
      );
    } else {
      console.error('No se proporcionó un nombre de usuario');
    }
  }
}
