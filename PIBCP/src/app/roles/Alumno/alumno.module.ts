import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AlumnoRoutingModule } from './alumno-routing.module';
import { DescargaComponent } from './descarga/descarga.component';
import { MaterialModule } from '../../material.module';
import { AlumnoComponent } from './alumno.component';
import { PopularidadComponent } from './popularidad/popularidad.component';
import { CalificacionPipePipe } from './calificacion-pipe.pipe';
import { CalificarComponent } from './calificar/calificar.component';
import { NavComponent } from './nav/nav.component';
import { ForoComponent } from './foro/foro.component';
import { PerfilComponent } from './perfil/perfil.component';


@NgModule({
  declarations: [AlumnoComponent, DescargaComponent, PopularidadComponent, CalificacionPipePipe, CalificarComponent, NavComponent, ForoComponent, PerfilComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AlumnoRoutingModule,
    MaterialModule
  ],
  providers: [CalificacionPipePipe],
  bootstrap: [AlumnoComponent]
})
export class AlumnoModule { }
