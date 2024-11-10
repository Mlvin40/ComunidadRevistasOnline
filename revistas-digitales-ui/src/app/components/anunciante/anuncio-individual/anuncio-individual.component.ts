import { Component, Input, OnInit } from '@angular/core';
import { AnuncioService } from '../../../services/anuncio/anuncio.service';
import { Anuncio } from '../../../entidades/Anuncio';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-anuncio-individual',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './anuncio-individual.component.html',
  styleUrls: ['./anuncio-individual.component.css']
})
export class AnuncioIndividualComponent implements OnInit {
  @Input() pathMostrado!: string; // El ID del anuncio recibido como parámetro
  anuncio: Anuncio | undefined;

  constructor(
    private anuncioService: AnuncioService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.anuncioService.obtenerAnuncioRandom().subscribe(
      (data) => {
        this.anuncio = data;
        this.almacenarAnuncioEfectivo();
      },
      (error) => {
        console.error('Error al obtener el anuncio:', error);
      }
    );
  }

  almacenarAnuncioEfectivo(): void {
    if (!this.anuncio) {
      console.error('No hay anuncio para almacenar');
      return;
    }

    const formData = new FormData();
    formData.append('idanuncio', this.anuncio.idAnuncio.toString());
    formData.append('tipoAnuncio', this.anuncio.tipoAnuncio);
    formData.append('nombreAnunciante', this.anuncio.nombreAnunciante);
    formData.append('pathMostrado', this.pathMostrado);

    // Guardar el anuncio mostrado en la base de datos
    this.anuncioService.guardarAnuncioMostrado(formData).subscribe(
      (response) => {
        console.log('Anuncio mostrado guardado correctamente:', response);
      },
      (error) => {
        console.error('Error al guardar el anuncio mostrado:', error);
      }
    );
  }

  convertirImagen(imagen: Blob): string {
    return `data:image/png;base64,${imagen}`;
  }

  sanitizarURL(url: string): SafeResourceUrl {
    let embedUrl: string;
    
    if (this.esYoutube(url)) {
      const videoId = this.extraerIdDeYoutube(url);
      embedUrl = `https://www.youtube.com/embed/${videoId}`;
    } else if (this.esVimeo(url)) {
      const videoId = this.extraerIdDeVimeo(url);
      embedUrl = `https://player.vimeo.com/video/${videoId}`;
    } else if (this.esDailymotion(url)) {
      const videoId = this.extraerIdDeDailymotion(url);
      embedUrl = `https://www.dailymotion.com/embed/video/${videoId}`;
    } else {
      embedUrl = url;
    }
    
    return this.sanitizer.bypassSecurityTrustResourceUrl(embedUrl);
  }

  private esYoutube(url: string): boolean {
    return /(?:https?:\/\/)?(?:www\.)?(?:youtube\.com|youtu\.be)/.test(url);
  }

  private esVimeo(url: string): boolean {
    return /(?:https?:\/\/)?(?:www\.)?vimeo\.com/.test(url);
  }

  private esDailymotion(url: string): boolean {
    return /(?:https?:\/\/)?(?:www\.)?dailymotion\.com/.test(url);
  }

  private extraerIdDeYoutube(url: string): string {
    const regex = /(?:youtube\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|.*[?&]v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})/;
    const match = url.match(regex);
    return match ? match[1] : '';
  }

  private extraerIdDeVimeo(url: string): string {
    const regex = /vimeo\.com\/(\d+)/;
    const match = url.match(regex);
    return match ? match[1] : '';
  }

  private extraerIdDeDailymotion(url: string): string {
    const regex = /dailymotion\.com\/video\/([^_]+)/;
    const match = url.match(regex);
    return match ? match[1] : '';
  }
}
