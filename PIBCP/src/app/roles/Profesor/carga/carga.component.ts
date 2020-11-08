import { AfterViewInit, Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Persona } from '../../../models/persona';
import { Archivo } from '../../../models/archivo';
import { Nucleotematico } from '../../../models/nucleo';
import { PersonaServiceService } from '../../../services/persona-service.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from '../../../../environments/environment.prod';
import { FireStorageServiceService } from '../service/fire-storage-service.service';
import { ArchivoServiceService } from '../../../services/archivo-service.service';
import { FileItem } from '../../../models/fileitem';
import { finalize } from 'rxjs/operators';
import swal from 'sweetalert2';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-carga',
  templateUrl: './carga.component.html',
  styleUrls: ['./carga.component.css']
})
export class CargaComponent implements OnInit{
  
  personaReg: FormGroup;
  rol = '';
  it: File = null;
  public porcentaje = 0;
  public finalizado = false;
  persona: Persona = new Persona();
  archivo: Archivo = new Archivo();
  stringRegx =  /^[ A-Za-z]+$/ ;
  numberRegx = /^[0-9]*$/;
  ide = 0;
  displayedColumns: string[] = [ 'profesor','nucleo', 'titulo', 'url','fecha'];
  dataSource = new MatTableDataSource<Archivo>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  cantidad = 0;
  user = '';
  constructor(private fb: FormBuilder, private pSer: PersonaServiceService,
              private firebaseStorage: FireStorageServiceService, private aSer: ArchivoServiceService) { }

  ngOnInit(): void {
    const helper = new JwtHelperService();
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    const decodeToken = helper.decodeToken(token);
    this.user = decodeToken.user_name;
    const rol: string = decodeToken.authorities[0];
    this.rol = rol;
    this.obtenerUser();
    this.validarReg();
    this.Iniciar(0);
    this.cargarPaginador();
  }

  obtenerUser() {
    this.pSer.obtenerusuario(this.user).subscribe((p) => { this.persona = p, this.ide = p.id });
  }

  cargarPaginador() {
    this.dataSource.paginator = this.paginator;
  }

  cambioPagina(e: any) {
      this.Iniciar(e.pageIndex);
  }

  Iniciar(page: number): void {
    this.aSer.traerArchivos(this.user, page).subscribe(res => {
      console.log(res.content);
      this.dataSource = new MatTableDataSource(res.content);
        this.cantidad = res.totalElements;
        this.paginator._intl.itemsPerPageLabel = 'Registros por pagina';
        this.paginator._intl.nextPageLabel = 'Siguiente';
        this.paginator._intl.previousPageLabel = 'Anterior';
    });
  }

  validarReg() {
    this.personaReg = this.fb.group({
      nucleo_archivo: new FormControl(this.archivo.nucleo_archivo, [Validators.required]),
      titulo: new FormControl(this.archivo.titulo, [Validators.required, Validators.pattern(this.stringRegx)]),
      archivo: new FormControl(null, Validators.required),
    })
  }

  public cambioArchivo(event) {
    if (event.target.files.length > 0) {
      for (let i = 0; i < event.target.files.length; i++) {
        this.it = event.target.files[i];
      }
    } else {
      return;
    }
  }

  public subirFire() {
    console.log(this.it);
    let referencia = this.firebaseStorage.referenciaCloudStorage(this.it.name);
    let tarea = this.firebaseStorage.tareaCloudStorage(this.it.name, this.it);

    tarea.percentageChanges().subscribe((porcentaje) => {
      this.porcentaje = Math.round(porcentaje);
      if (this.porcentaje == 100) {
        this.finalizado = true;
      }
    });

    tarea.snapshotChanges().pipe(finalize(() => {
      referencia.getDownloadURL().subscribe((URL) => {
        console.log('esta es la url',URL)
        const fecha = new Date();
        this.archivo.nucleo_archivo = this.personaReg.controls.nucleo_archivo.value;
        this.archivo.persona_archivo = this.persona;
        this.archivo.titulo = this.personaReg.controls.titulo.value;
        this.archivo.fecha = fecha;
        this.archivo.url = URL;
        this.aSer.subirArchivo(this.archivo).subscribe(data => {
          swal.fire('Cambios guardados', 'success');
          this.Iniciar(0);
          this.finalizado = false;
          this.porcentaje = 0;
        });
      });
    }
    )).subscribe();    
  }


}
