import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfesorRoutingModule } from './profesor-routing.module';
import { CargaComponent } from './carga/carga.component';
import { ProfesorComponent } from './profesor.component';
import { MaterialModule } from '../../material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularFireModule } from '@angular/fire';
import { AngularFireStorageModule } from '@angular/fire/storage';
import { environment } from '../../../environments/environment.prod';
import { ForoComponent } from './foro/foro.component';
import { NavComponent } from './nav/nav.component';
import { PerfilComponent } from './perfil/perfil.component';

@NgModule({
  declarations: [ProfesorComponent, CargaComponent, ForoComponent, NavComponent, PerfilComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ProfesorRoutingModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [ProfesorComponent]
})
export class ProfesorModule { }
