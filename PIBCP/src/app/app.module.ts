import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PrincipalComponent } from './pages/principal/principal.component';
import { UsuarioComponent } from './pages/usuario/usuario.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { JwtModule } from '@auth0/angular-jwt';
import { GuardServiceService } from './services/guard-service.service';
import { PersonaServiceService } from './services/persona-service.service';
import { PublicacionServiceService } from './services/publicacion-service.service';
import { RolServiceService } from './services/rol-service.service';
import { SesionServiceService } from './services/sesion-service.service';
import { TiposServiceService } from './services/tipos-service.service';
import { environment } from '../environments/environment.prod';
import { AngularFireModule } from '@angular/fire';
import { AngularFireStorageModule } from '@angular/fire/storage';
import { ErrorComponent } from './pages/error/error.component';
export function tokenGetter() {
  return sessionStorage.getItem(environment.TOKEN_NAME);
}

@NgModule({
  declarations: [
    AppComponent,
    PrincipalComponent,
    UsuarioComponent,
    RegistroComponent,
    NavbarComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter,
        whitelistedDomains: ['localhost:8080'],
        blacklistedRoutes: ['localhost:8080/faca/personas/registrar'],
      }
    }),
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireStorageModule
  ],
  providers: [GuardServiceService, PersonaServiceService, PublicacionServiceService, RolServiceService, SesionServiceService, TiposServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
