import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Archivo } from '../models/archivo';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { map, catchError } from 'rxjs/operators';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ArchivoServiceService {

  constructor(private http: HttpClient) { }

  subirArchivo(archivo: Archivo): Observable<Archivo> {
    return this.http.post<Archivo>(`${environment.URL_EP}/archivos/subir`, archivo).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );;
  }

  traerArchivos(user: string, page: number): Observable<any>{
    return this.http.get<any>(`${environment.URL_EP}/archivos/profesor/${user}/${page}`).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }

  traerArchivosNucleo(user: number, page: number): Observable<any>{
    return this.http.get<any>(`${environment.URL_EP}/archivos/profesor/nucleo/${user}/${page}`).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }
}
