import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';
import { PortalComponent } from './components/portal/portal.component';
import { EditorHomeComponent } from './components/editor/editor-home/editor-home.component';
import { CrearRevistaComponent } from './components/editor/crear-revista/crear-revista.component';
import { ConfiguracionUsuarioComponent } from './components/en-comun/configuracion-usuario/configuracion-usuario.component';
import { MisRevistasComponent } from './components/editor/mis-revistas/mis-revistas.component';
import { EditarRevistaComponent } from './components/editor/editar-revista/editar-revista.component';
import { PublicarComponent } from './components/editor/publicar/publicar.component';
import { VerPublicacionesComponent } from './components/en-comun/ver-publicaciones/ver-publicaciones.component';
import { AnuncianteHomeComponent } from './components/anunciante/anunciante-home/anunciante-home.component';
import { CarteraUsuarioComponent } from './components/en-comun/cartera-usuario/cartera-usuario.component';
import { PerfilUsuarioComponent } from './components/en-comun/perfil-usuario/perfil-usuario.component';
import { ComprarAnuncioComponent } from './components/anunciante/comprar-anuncio/comprar-anuncio.component';
import { AnuncioComponent } from './components/anunciante/anuncio/anuncio.component';
import { VerAnunciosComponent } from './components/anunciante/ver-anuncios/ver-anuncios.component';
import { EditarAnuncioComponent } from './components/anunciante/editar-anuncio/editar-anuncio.component';
import { VerRevistasDisponiblesComponent } from './components/suscriptor/ver-revistas-disponibles/ver-revistas-disponibles.component';
import path from 'path';
import { SuscriptorHomeComponent } from './components/suscriptor/suscriptor-home/suscriptor-home.component';
import { MisSuscripcionesComponent } from './components/suscriptor/mis-suscripciones/mis-suscripciones.component';
import { CambiarCostoRevistaComponent } from './components/administrador/cambiar-costo-revista/cambiar-costo-revista.component';
import { AdminHomeComponent } from './components/administrador/admin-home/admin-home.component';
import { CostoGlobalRevistaComponent } from './components/administrador/costo-global-revista/costo-global-revista.component';
import { CostoAnunciosComponent } from './components/administrador/costo-anuncios/costo-anuncios.component';
import { PrecioOcultacionAnunciosComponent } from './components/administrador/precio-ocultacion-anuncios/precio-ocultacion-anuncios.component';
import { ReporteComentariosRevistaComponent } from './components/editor/reporte-comentarios-revista/reporte-comentarios-revista.component';
import { ReporteSuscripcionRevistaComponent } from './components/editor/reporte-suscripcion-revista/reporte-suscripcion-revista.component';
import { ReporteMegustaRevistaComponent } from './components/editor/reporte-megusta-revista/reporte-megusta-revista.component';
import { ReporteComentariosTopComponent } from './components/administrador/reporte-comentarios-top/reporte-comentarios-top.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'portal', component: PortalComponent },
  { path: '', redirectTo: '/portal', pathMatch: 'full' },// Redirige a portal por defecto
  { path: 'editorHome', component: EditorHomeComponent },
  { path: 'crearRevista', component: CrearRevistaComponent },
  { path: 'configuracionPerfil', component: ConfiguracionUsuarioComponent },
  { path: 'carteraUsuario', component: CarteraUsuarioComponent },
  { path: 'perfilUsuario/:nombreUsuario', component: PerfilUsuarioComponent },

  // Para el usuario editor
  { path: 'misRevistas', component: MisRevistasComponent },
  { path: 'editarRevista/:nombre', component: EditarRevistaComponent },
  { path: 'publicar/:nombre', component: PublicarComponent },
  { path: 'verPublicaciones/:nombre', component: VerPublicacionesComponent },
  { path: 'reporteDeComentario', component: ReporteComentariosRevistaComponent},
  { path: 'reporteDeSuscripcion', component: ReporteSuscripcionRevistaComponent},
  { path: 'reporteMasGustadas', component: ReporteMegustaRevistaComponent}, 


  // Rutas del anunciante
  { path : 'anuncianteHome', component: AnuncianteHomeComponent },
  { path : 'comprarAnuncio', component: ComprarAnuncioComponent },
  { path : 'mostrarAnuncio', component: AnuncioComponent },
  { path : 'verMisAnuncios', component: VerAnunciosComponent },
  { path : 'editarAnuncio/:id', component: EditarAnuncioComponent },
  

  //Rutas del suscriptor
  { path: 'suscriptorHome', component: SuscriptorHomeComponent},
  { path: 'verRevistasDisponibles', component: VerRevistasDisponiblesComponent },
  { path: 'misSuscripciones', component: MisSuscripcionesComponent },


  // Ruta del administrador
  { path: 'adminHome', component: AdminHomeComponent },
  { path: 'cambiarCostorRevista', component: CambiarCostoRevistaComponent },
  { path: 'cambiarCostoGlobalRevista', component: CostoGlobalRevistaComponent },
  { path: 'cambiarCostoAnuncios', component: CostoAnunciosComponent },
  { path: 'cambiarCostoOcultacionAnuncio', component: PrecioOcultacionAnunciosComponent },
  { path: 'reporteComentariosTop', component: ReporteComentariosTopComponent }, 
];
