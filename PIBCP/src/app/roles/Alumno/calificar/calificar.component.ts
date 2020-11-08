import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Persona } from '../../../models/persona';
import { PersonaServiceService } from '../../../services/persona-service.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from '../../../../environments/environment.prod';
import swal from 'sweetalert2';
import { Calificacion } from '../../../models/calificacion';
import { StarRatingComponent } from 'ng-starrating';
import { CalificacionPipePipe } from '../calificacion-pipe.pipe';

@Component({
  selector: 'app-calificar',
  templateUrl: './calificar.component.html',
  styleUrls: ['./calificar.component.css']
})
export class CalificarComponent implements OnInit {
  profesor: Persona = new Persona();
  rol = '';
  user = '';
  id = 0;
  idP = 0;
  total: number = 0.;
  totale = 5;
  constructor(private pSer: PersonaServiceService, private router: Router,
    private activatedRouter: ActivatedRoute, private pipt: CalificacionPipePipe) { }

  ngOnInit(): void {
    const helper = new JwtHelperService();
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    const decodeToken = helper.decodeToken(token);
    this.user = decodeToken.user_name;
    this.pSer.obtenerusuario(this.user).subscribe((p) => this.id = p.id);
    this.cargarProfesor();
  }

  cargarProfesor() {
    this.activatedRouter.params.subscribe(param => {
      this.idP = param['id'];
      this.pSer.obtenerusuarioXId(this.idP).subscribe((p) => this.profesor = p);
    })
  }

  onRate($event: { oldValue: number, newValue: number, starRating: StarRatingComponent }) {
    const calificacion = new Calificacion();
    calificacion.id_alumno = this.id;
    calificacion.persona_profesor = this.profesor;
    calificacion.voto = true;
    calificacion.cantidad = $event.newValue;
    this.pSer.calificarProfesores(this.idP, calificacion).subscribe(p => {
      swal.fire('Calificacion enviada correctamente', 'success');
      const final = this.pipt.transform(p.calificacion);
      this.pSer.totalProfesor(p.id, final).subscribe();
    });
    this.router.navigate(['/alumnos/main']);
  }
}
