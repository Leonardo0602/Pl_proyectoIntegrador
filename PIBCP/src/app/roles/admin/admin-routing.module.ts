import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin.component';
import { GuardServiceService } from '../../services/guard-service.service';
import { NucleosComponent } from './nucleos/nucleos.component';
import { UsuariosComponent } from './usuarios/usuarios.component';

const routes: Routes = [
  {
    path: 'main',
    component: AdminComponent,
    canActivate: [GuardServiceService],
    children: [
      { path: '', redirectTo: 'nucleos', pathMatch: 'full' },
      {
        path: 'nucleos',
        component: NucleosComponent
      },
      {
        path: 'usuarios',
        component: UsuariosComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
