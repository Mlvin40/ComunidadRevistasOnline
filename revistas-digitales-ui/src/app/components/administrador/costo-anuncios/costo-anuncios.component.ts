import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdministradorService } from '../../../services/administrador/administrador.service';
import { AnuncioService } from '../../../services/anuncio/anuncio.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-costo-anuncios',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './costo-anuncios.component.html',
  styleUrls: ['./costo-anuncios.component.css'],
})
export class CostoAnunciosComponent implements OnInit {
  preciosForm: FormGroup;
  precios = { texto: 0, textoImagen: 0, video: 0 };
  mensaje: string = '';

  private tipoAnuncioMap: { [key: string]: string } = {
    texto: 'TEXTO',
    textoImagen: 'TEXTO_IMAGEN',
    video: 'VIDEO'
  };

  constructor(
    private fb: FormBuilder,
    private anuncioService: AnuncioService,
    private administradorService: AdministradorService
  ) {
    // Configuración del formulario reactivo
    this.preciosForm = this.fb.group({
      texto: [0, [Validators.required, Validators.min(0)]],
      textoImagen: [0, [Validators.required, Validators.min(0)]],
      video: [0, [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit(): void {
    this.cargarPreciosAnuncios();
  }

  cargarPreciosAnuncios(): void {
    this.anuncioService.obtenerPreciosAnuncios().subscribe(
      (precios) => {
        this.precios = precios;
        this.preciosForm.patchValue(precios);
      },
      (error) => {
        this.mensaje = 'Error al cargar los precios de los anuncios.';
      }
    );
  }

  actualizarPrecio(tipo: string): void {
    const nuevoPrecio = this.preciosForm.get(tipo)?.value;

    if (nuevoPrecio >= 0) {
      const tipoAnuncioBD = this.tipoAnuncioMap[tipo];  // Mapear el tipo al formato requerido
      
      // Enviar el tipo de anuncio y el nuevo precio al backend
      this.administradorService.actualizarCostoAnuncio(tipoAnuncioBD, nuevoPrecio).subscribe(
        () => {
          this.mensaje = `El costo de ${tipo} ha sido actualizado exitosamente.`;
          this.cargarPreciosAnuncios(); // Recargar los precios para ver el cambio
        },
        () => {
          this.mensaje = `Error al actualizar el costo de ${tipo}.`;
        }
      );
    } else {
      this.mensaje = 'El precio no puede ser negativo.';
    }
  }
}
