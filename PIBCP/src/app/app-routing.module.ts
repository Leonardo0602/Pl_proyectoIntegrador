import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PrincipalComponent } from './pages/principal/principal.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { GuardServiceService } from './services/guard-service.service';
import { ErrorComponent } from './pages/error/error.component';
const routes: Routes = [
  {
    path: 'principal',
    component: PrincipalComponent
  },
  {
    path: 'registro',
    component: RegistroComponent
  },
  {
    path: 'error',
    component: ErrorComponent
  },
  {
    path: 'profesores',
    loadChildren: () => import('./roles/Profesor/profesor.module').then(a => a.ProfesorModule)
    , canActivate: [GuardServiceService]
  },
  {
    path: 'alumnos',
    loadChildren: () => import('./roles/Alumno/alumno.module').then(a => a.AlumnoModule)
    , canActivate: [GuardServiceService]
  },
  {
    path: 'admin',
    loadChildren: () => import('./roles/admin/admin.module').then(a => a.AdminModule)
    , canActivate: [GuardServiceService]
  },
  {
    path: '',
    redirectTo: '/principal',
    pathMatch: 'full'
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
