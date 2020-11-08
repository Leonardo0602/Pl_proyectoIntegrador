import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PopularidadComponent } from './popularidad/popularidad.component';
import { DescargaComponent } from './descarga/descarga.component';
import { CalificarComponent } from './calificar/calificar.component';
import { GuardServiceService } from '../../services/guard-service.service';
import { AlumnoComponent } from './alumno.component';
import { ForoComponent } from './foro/foro.component';
import { PerfilComponent } from './perfil/perfil.component';
const routes: Routes = [
  {
    path: 'main',
    component: AlumnoComponent,
    canActivate: [GuardServiceService],
    children: [
      { path: '', redirectTo: 'foro', pathMatch: 'full' },
      {
        path: 'foro',
        component: ForoComponent,
        canActivate: [GuardServiceService]
      },
      {
        path: 'perfil',
        component: PerfilComponent,
        canActivate: [GuardServiceService]
      },
      {
        path: 'archivos',
        component: DescargaComponent,
        canActivate: [GuardServiceService]
      },
      {
        path: 'popularidad',
        component: PopularidadComponent,
        canActivate: [GuardServiceService]
      },
      {
        path: 'calificar/:id',
        component: CalificarComponent,
        canActivate: [GuardServiceService]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlumnoRoutingModule { }
