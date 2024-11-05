import { Component } from '@angular/core';
import { AnuncioService } from '../../../services/anuncio/anuncio.service';
import { Anuncio } from '../../../entidades/Anuncio';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-editar-anuncio',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './editar-anuncio.component.html',
  styleUrls: ['./editar-anuncio.component.css']
})
export class EditarAnuncioComponent {
  anuncio: Anuncio | null = null;
  seCambioImagen = false;
  mensajeEstado: string | null = null; // Propiedad para el mensaje de estado

  constructor(
    private anuncioService: AnuncioService,
    private sanitizer: DomSanitizer,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.anuncioService.obtenerAnuncioPorId(id).subscribe(
      (data) => {
        this.anuncio = data;
      },
      (error) => {
        console.error('Error al obtener el anuncio:', error);
        this.router.navigate(['/misAnuncios']);
      }
    );
  }

  sanitizarImagen(imagen: Blob): string {
    return `data:image/png;base64,${imagen}`;
  }

  onFileSelected(event: Event): void {
    this.seCambioImagen = true;

    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files[0]) {
      const file = fileInput.files[0];
      this.anuncio!.imagen = file; // Guardar la imagen como Blob
    }
  }

  guardarCambios(): void {
    if (this.anuncio) {
      const formData = new FormData();
      formData.append('tipoAnuncio', this.anuncio.tipoAnuncio);
      formData.append('idAnuncio', this.anuncio.idAnuncio.toString());
      formData.append('contenidoTexto', this.anuncio.contenidoTexto || '');

      // Solo enviar la imagen si se cambió
      if (this.seCambioImagen) {
        formData.append('imagen', this.anuncio.imagen || '');
      }

      formData.append('urlVideo', this.anuncio.urlVideo || '');
      formData.append('activo', this.anuncio.activo.toString());
      formData.append('vencido', this.anuncio.vencido.toString());

      this.anuncioService.actualizarAnuncio(formData).subscribe(
        response => {
          console.log('Anuncio actualizado', response);
          this.mensajeEstado = 'Anuncio actualizado con éxito.'; // Mensaje de éxito
          this.router.navigate(['/misAnuncios']);
        },
        error => {
          console.error('Error al actualizar el anuncio:', error);
          this.mensajeEstado = 'Error al actualizar el anuncio. Por favor, inténtelo de nuevo.'; // Mensaje de error
        }
      );
    } else {
      console.error('No hay anuncio para guardar');
      this.mensajeEstado = 'No hay anuncio para guardar.'; // Mensaje cuando no hay anuncio
    }
  }
}
