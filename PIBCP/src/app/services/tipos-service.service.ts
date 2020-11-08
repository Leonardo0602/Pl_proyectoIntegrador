import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { TipoComentario } from '../models/tipocomentario';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TiposServiceService {


  constructor(private http: HttpClient) { }

  traerTipos(): Observable<TipoComentario[]>{
    return this.http.get<TipoComentario[]>(`${environment.URL_EP}/tipos/tipo`).pipe(map(p => p as TipoComentario[]));
  }
}
