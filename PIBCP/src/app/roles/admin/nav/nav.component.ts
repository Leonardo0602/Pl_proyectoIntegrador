import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Persona } from 'src/app/models/persona';
import { PersonaServiceService } from 'src/app/services/persona-service.service';
import { SesionServiceService } from 'src/app/services/sesion-service.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  
  constructor(private pServ: PersonaServiceService, private service: SesionServiceService, private router: Router) { }
  
  persona: Persona = new Persona();
  rol = '';
  ngOnInit(): void {
    const helper = new JwtHelperService();
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    const decodeToken = helper.decodeToken(token);
    const user = decodeToken.user_name;
    const rol: string = decodeToken.authorities[0];
    this.rol = rol;
    this.pServ.obtenerusuario(user).subscribe((p) => this.persona = p);
  }

  cerrarSesion() {
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    this.service.cerrarSession(token).subscribe(() => {
      sessionStorage.clear();
      this.router.navigate(['/principal']);
    });
  }
}
