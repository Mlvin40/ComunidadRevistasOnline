import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { UsuariosService } from '../../usuarios/usuarios.service';

@Injectable({
  providedIn: 'root'
})
export class EsSuscriptorEditorService {
  constructor(private router: Router, private usuarioService: UsuariosService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    console.log(localStorage.getItem('rol'));

    if (!this.usuarioService.permisosSuscriptor() || !this.usuarioService.permisosEditor()) {
      this.router.navigate(['/login']);
      return false;
    }
    
    return true;
  }
}
