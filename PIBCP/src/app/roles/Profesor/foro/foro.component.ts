import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormGroupDirective, FormBuilder, FormControl, Validators } from '@angular/forms';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Persona } from 'src/app/models/persona';
import { Publicacion } from 'src/app/models/publicacion';
import { TipoComentario } from 'src/app/models/tipocomentario';
import { PersonaServiceService } from 'src/app/services/persona-service.service';
import { PublicacionServiceService } from 'src/app/services/publicacion-service.service';
import { TiposServiceService } from 'src/app/services/tipos-service.service';
import { environment } from 'src/environments/environment.prod';
import swal from 'sweetalert2';

@Component({
  selector: 'app-foro',
  templateUrl: './foro.component.html',
  styleUrls: ['./foro.component.css']
})
export class ForoComponent implements OnInit {

  
  publicacion: FormGroup;
  tipos: Array<TipoComentario> = [];
  persona: Persona = new Persona();
  publicacionPost = new Publicacion();
  muro: Array<Publicacion> = [];
  activado = true;
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective;

  constructor(private pServ: PersonaServiceService, private tSer: TiposServiceService, private puServ: PublicacionServiceService, private fb: FormBuilder) { }
  
  ngOnInit(): void {
    const helper = new JwtHelperService();
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    const decodeToken = helper.decodeToken(token);
    const user = decodeToken.user_name;
    this.pServ.obtenerusuario(user).subscribe((p) => this.persona = p);
    this.tSer.traerTipos().subscribe(t => this.tipos = t as TipoComentario[]);
    this.cargarPublicaciones();
    this.validarPublicacion();
  }

  validarPublicacion() {
    this.publicacion = this.fb.group({
      tipo: new FormControl(this.publicacionPost.tipo, [Validators.required]),
      asunto: new FormControl(this.publicacionPost.asunto, [Validators.required])
    });
    this.publicacion.patchValue({asunto: this.publicacionPost.asunto});
  }

  guardar() {
    if (!this.publicacion.valid) {
      return;
    }
    this.publicacionPost.fecha = new Date();
    this.publicacionPost.asunto = this.publicacion.controls.asunto.value;
    this.publicacionPost.tipo = this.publicacion.controls.tipo.value;
    this.publicacionPost.persona_publicacion = this.persona;

    
    this.puServ.crearPublicacion(this.publicacionPost).subscribe(p => {
      swal.fire('Publicación creada correctamente', 'success');
      this.cargarPublicaciones();
    });

  }

  cargarPublicaciones() {
    this.puServ.obtenerPublicaciones().subscribe(p => this.muro = p as Publicacion[]);
  }

  eliminar(id: number) {
    this.puServ.eliminarPublicacion(id).subscribe(
      response => {
        this.muro = this.muro.filter(c => c.id !== id);
      }
    );;
  }
}
