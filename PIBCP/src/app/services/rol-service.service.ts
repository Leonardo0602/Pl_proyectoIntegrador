import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
import { Rol } from '../models/rol';

@Injectable({
  providedIn: 'root'
})
export class RolServiceService {

  constructor(private http: HttpClient) { }

  traerRoles(): Observable<Rol[]> {
    return this.http.get<Rol[]>(`${environment.URL_EP}/roles/rol`).pipe(map(p => p as Rol[]));
  }
}
