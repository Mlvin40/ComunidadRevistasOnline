import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PublicacionService } from '../../../services/publicacion/publicacion.service';
import { Publicacion } from '../../../entidades/Publicacion';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ver-publicaciones',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ver-publicaciones.component.html',
  styleUrls: ['./ver-publicaciones.component.css']
})
export class VerPublicacionesComponent implements OnInit {
  publicaciones: Publicacion[] = [];
  mensajeError: string = '';

  constructor(private publicacionService: PublicacionService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Obtiene el nombre de la revista desde los parámetros de la ruta
    const nombreRevista = this.route.snapshot.paramMap.get('nombre') || 'Sin nombre';
    this.cargarPublicaciones(nombreRevista);
  }

  cargarPublicaciones(nombreRevista: string): void {
    this.publicacionService.obtenerPublicacionesPorRevista(nombreRevista).subscribe({
      next: (publicaciones) => (this.publicaciones = publicaciones),
      error: (error) => (this.mensajeError = 'Error al cargar las publicaciones.')
    });
  }

  verPDF(publicacion: Publicacion): void {
    
    // Convertir imagen binaria a Base64 para mostrarla
    if (publicacion.archivoPDF) {
      const url = `data:file/pdf;base64,${publicacion.archivoPDF}`;
    // Abrir la URL en una nueva pestaña
    window.open(url);
  }
}

}
