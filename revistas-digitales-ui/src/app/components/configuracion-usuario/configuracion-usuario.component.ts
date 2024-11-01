import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ConfiguracionUsuarioService } from '../../services/configuracion-usuario/configuracion-usuario.service';
import { Usuario } from '../../entidades/Usuario';
import { TipoUsuario } from '../../entidades/TipoUsuario';

@Component({
  selector: 'app-configuracion-usuario',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './configuracion-usuario.component.html',
  styleUrls: ['./configuracion-usuario.component.css'],
})
export class ConfiguracionUsuarioComponent implements OnInit {
  nombreUsuario: string | null = '';
  usuario: Usuario | null = null; // Almacena los datos del usuario
  texto: string = ''; // Para el texto del perfil
  fotoPerfil: File | null = null; // Para almacenar la foto seleccionada
  rolTexto: string = ''; // Para almacenar el rol del usuario como texto

  constructor(private configuracionUsuarioService: ConfiguracionUsuarioService, private http: HttpClient) {}

  ngOnInit(): void {
    this.nombreUsuario = this.configuracionUsuarioService.obtenerNombreUsuarioDesdeToken();

    if (this.nombreUsuario) {
      this.configuracionUsuarioService.obtenerDatosUsuario(this.nombreUsuario).subscribe(
        (datosUsuario: Usuario) => {
          this.nombreUsuario
          this.usuario = datosUsuario;
          this.texto = datosUsuario.texto;
          // Asumiendo que el rol se devuelve como un string, puedes convertirlo a texto.
          this.rolTexto = TipoUsuario[datosUsuario.rol]; // Conversión a texto
        },
        (error) => {
          console.error('Error al obtener datos del usuario', error);
        }
      );
    }
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.fotoPerfil = input.files[0];
    }
  }

  guardarCambios(): void {
    // Lógica para guardar cambios en el perfil
  }
}