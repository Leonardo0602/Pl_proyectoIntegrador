import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, NgForm } from '@angular/forms';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Nucleotematico } from 'src/app/models/nucleo';
import { Persona } from 'src/app/models/persona';
import { NucleoTematicoService } from 'src/app/services/nucleo-tematico.service';
import { PersonaServiceService } from 'src/app/services/persona-service.service';
import { environment } from 'src/environments/environment.prod';
import swal from 'sweetalert2';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  constructor(private pSer: PersonaServiceService, private fb: FormBuilder, private nServ: NucleoTematicoService) { }
  personaReg: FormGroup;
  toppings = new FormControl();
  persona: Persona = new Persona();
  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  stringRegx =  /^[a-zA-Z]+$/ ;
  numberRegx = /^[0-9]*$/;
  rol = '';
  editable = false;
  desactivado = true;
  desactivado2 = true;
  imagenI = '';
  user = '';
  Photos: Array<{Url: string, Select: boolean}> = [
    { Url: './assets/avatars/1.png', Select: false },
    { Url: './assets/avatars/2.png', Select: false },
    { Url: './assets/avatars/3.png', Select: false },
    { Url: './assets/avatars/4.png', Select: false },
    { Url: './assets/avatars/5.png', Select: false },
    { Url: './assets/avatars/6.png', Select: false }
  ]

  ngOnInit(): void {
    const helper = new JwtHelperService();
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    const decodeToken = helper.decodeToken(token);
    this.user = decodeToken.user_name;
    const rol: string = decodeToken.authorities[0];
    this.rol = rol;
    this.obtenerUsuario();
    
  }

  obtenerUsuario() {
    this.pSer.obtenerusuario(this.user).subscribe((p) => { this.persona = p , this.imagenI = p.url_foto});
  }


  seleccionarImagen(url: any) {
    this.Photos.forEach(av => av.Select = false);
    url.Select = true;
    this.persona.url_foto = url.Url;
  }

  cancelar() {
    this.persona.url_foto = this.imagenI;
  }

  activar() {
    this.desactivado = false;
  }

  guardar(f : NgForm) {
    if (!f.valid) {
      return;
    }
    this.pSer.editarUsuario(this.persona).subscribe(p => {
      swal.fire('Cambios guardados', 'success')
      this.desactivado = true;
    });
  }
}
