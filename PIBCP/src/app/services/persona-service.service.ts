import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Persona } from '../models/persona';
import { environment } from 'src/environments/environment.prod';
import { map, catchError } from 'rxjs/operators';
import { TipoComentario } from 'src/app/models/tipocomentario';
import { Nucleotematico } from '../models/nucleo';
import { Calificacion } from '../models/calificacion';
import { IPersonaBestDTO } from '../models/ipersonabestdto';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class PersonaServiceService {

  constructor(private http: HttpClient) { }

  crearUsuario(persona: Persona): Observable<Persona>{
    return this.http.post<Persona>(`${environment.URL_EP}/personas/registrar`, persona).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }

  obtenerusuario(correo: string): Observable<Persona>{
    return this.http.get<Persona>(`${environment.URL_EP}/personas/persona/usuario/${correo}`).pipe(map(p => p as Persona));
  }

  obtenerusuarioXId(id: number): Observable<Persona>{
    return this.http.get<Persona>(`${environment.URL_EP}/personas/persona/perfil/${id}`).pipe(map(p => p as Persona));
  }

  editarUsuario(persona: Persona): Observable<Persona>{
    return this.http.put<Persona>(`${environment.URL_EP}/personas/persona/editar`, persona).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }

  agregarNucleoUsuario(id: number, nucleo: Array<Nucleotematico>): Observable<Persona>{
    return this.http.put<Persona>(`${environment.URL_EP}/personas/persona/agregarnucleo/${id}`, nucleo).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }

  eliminarRelacion(idP: number, idN: number): Observable<Persona>{
    return this.http.delete<Persona>(`${environment.URL_EP}/personas/persona/eliminar/${idP}/${idN}`).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }

  calificarProfesores(id: number, calificacion: Calificacion): Observable<Persona>{
    return this.http.put<Persona>(`${environment.URL_EP}/personas/persona/calificar/${id}`, calificacion).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }

  totalProfesor(id: number, calificacion: number): Observable<Persona>{
    return this.http.put<Persona>(`${environment.URL_EP}/personas/persona/total/${id}`, calificacion).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }

  bestPersonas(): Observable<IPersonaBestDTO[]> {
    return this.http.get<IPersonaBestDTO[]>(`${environment.URL_EP}/personas/persona/best`).pipe(map(p => p as IPersonaBestDTO[]));
  }

  usuariosXRolPage(rol: number, page: number): Observable<any>{
    return this.http.get<any>(`${environment.URL_EP}/personas/persona/admin/${rol}/${page}`);
  }
}
