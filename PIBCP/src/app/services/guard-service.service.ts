import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SesionServiceService } from './sesion-service.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from '../../environments/environment.prod';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class GuardServiceService implements CanActivate {

  constructor(private serv: SesionServiceService, private router: Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    
    let tok = '';
    let ur = '';
    if (this.serv.isLoged) {
      const helper = new JwtHelperService();
      const token = sessionStorage.getItem(environment.TOKEN_NAME);
      tok = token;
      
      if (!helper.isTokenExpired(tok)) {
        const url = state.url;
        ur = url;
        const decodeToken = helper.decodeToken(tok);
        const rol: string = decodeToken.authorities[0];
        console.log(url);
        
        if (url.includes('alumnos') && rol === 'Alumno') {
          return true;
        }

        if (url.includes('profesores') && rol === 'Profesor') {
          return true;
        }

        if (url.includes('admin') && rol === 'Admin') {
          return true;
        }

        this.router.navigate(['/error']);
        return false;
      } else {
        if (tok !== null) {
          this.serv.cerrarSession(tok);
          sessionStorage.clear();
          this.router.navigate(['/registro']);
          return false;
        } else {
          sessionStorage.clear();
          this.router.navigate(['/registro']);
          swal.fire('Ups!', '¡Lo sentimos! \n Debes iniciar sesion :c', 'error');
          return false;
        } 
      }
    }else {
      if (tok !== null) {
        this.serv.cerrarSession(tok);
        sessionStorage.clear();
        this.router.navigate(['/registro']);
        swal.fire('Ups!', 'Debes iniciar sesion', 'error');
        return false;
      } else {
        sessionStorage.clear();
        this.router.navigate(['/registro']);
        swal.fire('Ups!', 'Debes iniciar sesion', 'error');
        return false;
      }
    }
  }
}

