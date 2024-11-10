import { Component } from '@angular/core';
import { AnuncioService } from '../../../services/anuncio/anuncio.service';
import { Anuncio } from '../../../entidades/Anuncio';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { CommonModule } from '@angular/common'; // Import CommonModule

@Component({
  selector: 'app-anuncio',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './anuncio.component.html',
  styleUrls: ['./anuncio.component.css']
})
export class AnuncioComponent {
  anuncios: Anuncio[] = [];

  constructor(
    private anuncioService: AnuncioService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.anuncioService.obtenerAnuncios().subscribe(
      (data) => {
        this.anuncios = data;
      },
      (error) => {
        console.error('Error al obtener los anuncios:', error);
      }
    );
  }
  
  //Convertir imagen en formato base64
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
        embedUrl = url; // Intentar usar la URL directa, asumiendo que es un video incrustable.
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
    
    // Este metodo sirve para extraer el id de un video de Dailymotion que se encuentra en la URL
    private extraerIdDeDailymotion(url: string): string {
      const regex = /dailymotion\.com\/video\/([^_]+)/;
      const match = url.match(regex);
      return match ? match[1] : '';
    }
    
}
