export interface Anuncio {
    idAnuncio: number;             // ID del anuncio
    tipoAnuncio: string;           // Tipo de anuncio (TEXTO, TEXTO_IMAGEN, VIDEO)
    contenidoTexto?: string;       // Contenido del anuncio en texto (opcional)
    imagen?: Blob;                 // Imagen del anuncio (si aplica)
    urlVideo?: string;             // URL del video (si aplica)
    nombreAnunciante: string;      // Nombre del anunciante
    fechaInicio: Date;           // Fecha de inicio del anuncio en formato 'YYYY-MM-DD'
    duracion: number;              // Duración en días
    fechaExpiracion: Date;       // Fecha de expiración en formato 'YYYY-MM-DD'
    vencido: boolean;              // Si el anuncio está vencido
    activo: boolean;               // Si el anuncio está activo
  }
  