import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Nucleotematico } from '../models/nucleo';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { map, catchError } from 'rxjs/operators';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class NucleoTematicoService {

  constructor(private http: HttpClient) { }

  traerNucleos(id: number): Observable<Nucleotematico[]>{
    return this.http.get<Nucleotematico[]>(`${environment.URL_EP}/nucleos/nucleo/${id}`).pipe(map(p => p as Nucleotematico[]));
  }

  traerNucleosAll(): Observable<Nucleotematico[]>{
    return this.http.get<Nucleotematico[]>(`${environment.URL_EP}/nucleos/nucleo`).pipe(map(p => p as Nucleotematico[]));
  }

  traerNucleosAllPage(page: number): Observable<any>{
    return this.http.get<any>(`${environment.URL_EP}/nucleos/nucleo/admin/${page}`);
  }

  guardarNucleo(nucleo: Nucleotematico): Observable<Nucleotematico>{
    return this.http.post<Nucleotematico>(`${environment.URL_EP}/nucleos/nucleo/guardar`, nucleo).pipe(
      catchError(e => {
        swal.fire(`Error ${e.error.error}`, e.error.message, 'error');
        return throwError(e);
      })
    );
  }
}
