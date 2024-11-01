import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';
import { PortalComponent } from './components/portal/portal.component';
import { EditorHomeComponent } from './components/editor/editor-home/editor-home.component';
import { CrearRevistaComponent } from './components/editor/crear-revista/crear-revista.component';
import { ConfiguracionUsuarioComponent } from './components/configuracion-usuario/configuracion-usuario.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'portal', component: PortalComponent },
  { path: '', redirectTo: '/portal', pathMatch: 'full' },// Redirige a portal por defecto
  { path: 'editorHome', component: EditorHomeComponent },
  { path: 'crearRevista', component: CrearRevistaComponent },
  { path: 'configuracionPerfil', component: ConfiguracionUsuarioComponent },
];
