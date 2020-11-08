import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Persona } from '../../models/persona';
import { PersonaLogin } from '../../models/personalogin';
import { RolServiceService } from '../../services/rol-service.service';
import { Rol } from '../../models/rol';
import { PersonaServiceService } from '../../services/persona-service.service';
import swal from 'sweetalert2';
import { Router } from '@angular/router';
import { SesionServiceService } from '../../services/sesion-service.service';
import { environment } from 'src/environments/environment.prod';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {
  
  personaLog: FormGroup;
  personaReg: FormGroup;
  persona = new Persona();
  login = new PersonaLogin();
  rol: Array<Rol> = [];
  selectedIndex: number = 0;
  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  stringRegx =  /^[a-zA-Z]+$/ ;
  numberRegx = /^[0-9]*$/;
  seleccionado = "";
  Photos: Array<{Url: string, Select: boolean}> = [
    { Url: './assets/avatars/1.png', Select: false },
    { Url: './assets/avatars/2.png', Select: false },
    { Url: './assets/avatars/3.png', Select: false },
    { Url: './assets/avatars/4.png', Select: false },
    { Url: './assets/avatars/5.png', Select: false },
    { Url: './assets/avatars/6.png', Select: false }
  ]
  constructor(private rolS: RolServiceService, private perS: PersonaServiceService,
              private router: Router, private fb: FormBuilder, private logS: SesionServiceService) { }

  ngOnInit(): void {
    this.traerRoles();
    this.ValidarLog();
    this.validarReg();
    
  }

  ValidarLog() {
    this.personaLog = this.fb.group({
      correo: new FormControl(this.login.correo, [Validators.required, Validators.pattern(this.emailRegx)]),
      clave: new FormControl(this.login.clave, [Validators.required])
    });
  }

  validarReg() {
    this.personaReg = this.fb.group({
      url_foto: new FormControl(this.persona.url_foto, [Validators.required]),
      nombre: new FormControl(this.persona.nombre, [Validators.required, Validators.pattern(this.stringRegx)]),
      apellido: new FormControl(this.persona.apellido, [Validators.required, Validators.pattern(this.stringRegx)]),
      documento: new FormControl(this.persona.documento, [Validators.required, Validators.pattern(this.numberRegx)]),
      correo: new FormControl(this.persona.correo, [Validators.required, Validators.pattern(this.emailRegx)]),
      clave: new FormControl(this.persona.clave, [Validators.required]),
      rol: new FormControl(this.persona.rol, [Validators.required])
    })
  }

  guardar(){
    if (!this.personaReg.valid) {
      return;
    }
    this.perS.crearUsuario(this.personaReg.value).subscribe(p => {
      swal.fire('Bienvenido', `${p.nombre}, Por favor inicia sesión`, 'success');
      this.selectedIndex = this.selectedIndex - 1;
      this.personaReg.controls.url_foto.setValue(" ");
      this.personaReg.reset();
    });
  }

  traerRoles() {
    this.rolS.traerRoles().subscribe(p => this.rol = p as Rol[]);
  }

  Login() {
    if (!this.personaLog.valid) {
      return;
    }
    this.logS.iniciarSesion(this.personaLog.value).subscribe(l => {
      sessionStorage.setItem(environment.TOKEN_NAME, l.access_token);
      const helper = new JwtHelperService();
      const decodeToken = helper.decodeToken(l.access_token);
      const rol: string = decodeToken.authorities[0];
      const role = rol;

      if (role === 'Alumno') {
        this.router.navigate(['/alumnos/main']);
      }
      if(role === 'Profesor'){
        this.router.navigate(['/profesores/main']);
      } 
      if(role === 'Admin'){
        this.router.navigate(['/admin/main']);
      }
      this.personaLog.reset();
    });

  }


  seleccionarImagen(url: any) {
    this.Photos.forEach(av => av.Select = false);
    url.Select = true;
    this.personaReg.controls.url_foto.setValue(url.Url);
  }
}
