import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
import swal from 'sweetalert2';
import { PersonaLogin } from '../models/personalogin';

@Injectable({
  providedIn: 'root'
})
export class SesionServiceService {

  private urlEndPoint = `${environment.URL_EP}/oauth/token`;

  /*
  POST /faca/oauth/token HTTP/1.1
  Host: localhost:8080
  Authorization: Basic YXByZW5kZWFwcHVkZWM6YXByZW5kZTIwMjA=
  Content-Type: application/x-www-form-urlencoded
  
  grant_type=password&username=asd@gmail.com&password=123
  */
  constructor(private http: HttpClient, private router: Router) { }

  iniciarSesion(login: PersonaLogin) {
    const body = `grant_type=password&username=${encodeURIComponent(login.correo)}&password=${encodeURIComponent(login.clave)}`;
    return this.http.post<any>(this.urlEndPoint, body, {
        headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8')
        .set('Authorization', 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD))
      }).pipe(
        catchError(e => {
          this.router.navigate(['/registro']);
          swal.fire('Tu usuario o contraseña son incorrectos :c',
          e.error.error_description, 'error');
          return throwError(e);
        })
      );
  }

  isLoged(): boolean {
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    if ( token != null ) {
      return true;
    } else {
      return false;
    }
  }

  cerrarSession(token: any): Observable<any> {
    return this.http.get<any>(`${environment.URL_EP}/cerrarSesion/anular/${token}`).pipe(
      catchError(e => {
        this.router.navigate(['/registro']);
        swal.fire(e.error.Error, e.error.error_description, 'error');
        return throwError(e);
      })
    );
  }
}
