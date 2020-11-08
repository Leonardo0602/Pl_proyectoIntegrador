import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Consultas } from '../models/consultas';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { map, catchError } from 'rxjs/operators';
import { ConsultasindiceDTO } from '../models/consultasindicedto';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ConsultaServiceService {

  constructor(private http: HttpClient) { }

  insertarConsulta(consulta: Consultas): Observable<Consultas>{
    return this.http.post<Consultas>(`${environment.URL_EP}/consultas/subir`, consulta).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );;
  }

  obtenerConsultas(): Observable<ConsultasindiceDTO[]>{
    return this.http.get<ConsultasindiceDTO[]>(`${environment.URL_EP}/consultas/todo`).pipe(map(p => p as ConsultasindiceDTO[]));
  }
}
