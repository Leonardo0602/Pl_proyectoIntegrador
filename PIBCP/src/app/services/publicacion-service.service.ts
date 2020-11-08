import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Publicacion } from '../models/publicacion';
import { map, catchError } from 'rxjs/operators';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class PublicacionServiceService {

  constructor(private http: HttpClient) { }

  crearPublicacion(publicacion: Publicacion): Observable<Publicacion>{
    return this.http.post<Publicacion>(`${environment.URL_EP}/publicaciones/publicar`, publicacion).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );;
  }

  obtenerPublicaciones(): Observable<Publicacion[]>{
    return this.http.get<Publicacion[]>(`${environment.URL_EP}/publicaciones/publico`).pipe(map(p => p as Publicacion[]));
  }

  eliminarPublicacion(id: number): Observable<Publicacion>{
    return this.http.delete<Publicacion>(`${environment.URL_EP}/publicaciones/eliminar/${id}`).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }
}
