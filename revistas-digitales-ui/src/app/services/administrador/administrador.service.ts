import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RestConstants } from '../rest-constants';
import { UsuariosService } from '../usuarios/usuarios.service';
import { Revista } from '../../entidades/Revista';
import { Observable } from 'rxjs';
import { TablaReporteGanancia } from '../../entidades/reporte/TablaReporteGanancia';
import { AnuncioComprado } from '../../entidades/reporte/AnuncioComprado';
import { ReporteEfectividadAnuncio } from '../../entidades/reporte/ReporteEfectividadAnuncio';


@Injectable({
  providedIn: 'root'
})
export class AdministradorService {


  private apiUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants, private usuarioService: UsuariosService) {
    this.apiUrl = `${this.restConstants.getApiURL()}/administrador`; // URL del backend para usuarios
  }

  obtenerRevistas(): Observable<Revista[]> {
    return this.http.get<Revista[]>(`${this.apiUrl}/obtenerRevistas`);
  }


  // Con este metodo se puede actualizar el precio de una revista
  actualizarPrecioRevista(nombreRevista: string, nuevoPrecio: number): Observable<any> {
    const params = new HttpParams()
      .set('nombreRevista', nombreRevista)
      .set('nuevoPrecio', nuevoPrecio.toString());

    return this.http.post(`${this.apiUrl}/actualizarPrecioRevista`, null, { params });
  }


  obtenerCostoGlobalRevista(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/obtenerCostoGlobalRevista`);
  }

  actualizarCostoGlobalRevista(nuevoPrecio: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/actualizarCostoGlobalRevista?nuevoPrecio=${nuevoPrecio}`, {});
  }

  actualizarCostoAnuncio(tipoAnuncio: string, nuevoCosto: number): Observable<any> {
    // Agregar tipoAnuncio y nuevoCosto como parámetros QueryParam en la URL
    return this.http.post(
      `${this.apiUrl}/actualizarCostoAnuncio?tipoAnuncio=${tipoAnuncio}&nuevoCosto=${nuevoCosto}`,
      {}
    );
  }

  obtenerCostoOcultacionRevista(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/costoOcultacionAnuncio`);
  }

  actualizarCostoOcultacionRevista(nuevoPrecio: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/ActualizarcostoOcultacionAnuncio?nuevoPrecio=${nuevoPrecio}`, {});
  }

  // Con este metodo se puede obtener el reporte de anuncios comprados con un rango de fechas
  obtenerReporteAnuncios(fechaInicio: string, fechaFin: string, tipoAnuncio?: string): Observable<AnuncioComprado[]> {
    let params = new HttpParams()
      .set('fechaInicio', fechaInicio)
      .set('fechaFin', fechaFin);

    if (tipoAnuncio) {
      params = params.set('tipoAnuncio', tipoAnuncio);
    }

    return this.http.get<AnuncioComprado[]>(`${this.apiUrl}/reporteAnunciosFiltro`, { params });
  }

  obtenerReporteGanancias(fechaInicio: string, fechaFin: string): Observable<TablaReporteGanancia> {
    const params = new HttpParams()
      .set('fechaInicio', fechaInicio)
      .set('fechaFin', fechaFin);

    return this.http.get<TablaReporteGanancia>(`${this.apiUrl}/reporteGanancias`, { params });
  }

  // Método para obtener el reporte de efectividad de anuncios
  obtenerReporteEfectividad(fechaInicio: string, fechaFin: string): Observable<ReporteEfectividadAnuncio[]> {
    let params = new HttpParams()
      .set('fechaInicio', fechaInicio)
      .set('fechaFin', fechaFin);

    return this.http.get<ReporteEfectividadAnuncio[]>(
      `${this.apiUrl}/reporteEfectividad`,
      { params }
    );
  }



}




