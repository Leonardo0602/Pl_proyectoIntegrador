import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CargaComponent } from './carga/carga.component';
import { GuardServiceService } from '../../services/guard-service.service';
import { ForoComponent } from './foro/foro.component';
import { PerfilComponent } from './perfil/perfil.component';
import { ProfesorComponent } from './profesor.component';

const routes: Routes = [
  {
    path: 'main',
    component: ProfesorComponent,
    canActivate: [GuardServiceService],
    children: [
      {
        path: '', redirectTo: 'foro', pathMatch: 'full' 
      },
      {
        path: 'foro',
        component: ForoComponent
      },
      {
        path: 'documentos',
        component: CargaComponent
      },
      {
        path: 'perfil',
        component: PerfilComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfesorRoutingModule { }
