import { Component, OnInit, ViewChild } from '@angular/core';
import { Persona } from '../../../models/persona';
import { Archivo } from '../../../models/archivo';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { ArchivoServiceService } from '../../../services/archivo-service.service';
import { CalificacionPipePipe } from '../calificacion-pipe.pipe';
import { Nucleotematico } from '../../../models/nucleo';
import { NucleoTematicoService } from '../../../services/nucleo-tematico.service';
import { ConsultaServiceService } from '../../../services/consulta-service.service';
import { Consultas } from 'src/app/models/consultas';

@Component({
  selector: 'app-descarga',
  templateUrl: './descarga.component.html',
  styleUrls: ['./descarga.component.css']
})
export class DescargaComponent implements OnInit {

  persona: Persona = new Persona();
  archivo: Archivo = new Archivo();
  nucleos: Array<Nucleotematico> = [];
  seleccionado: Consultas = new Consultas();
  displayedColumns: string[] = [ 'item','profesor','nucleo', 'titulo', 'fecha', 'url', 'calificacion', 'actions'];
  dataSource = new MatTableDataSource<Archivo>();
  @ViewChild(MatPaginator ,{ static : false} ) paginator: MatPaginator;
  cantidad = 0;
  totales = 5;
  constructor(private aSer: ArchivoServiceService, private nSer: NucleoTematicoService, private cSer: ConsultaServiceService) { }

  ngOnInit(): void {
    //this.Iniciar(0);
    this.cargarPaginador();
    this.traerNucleos();
  }

  cargarPaginador() {
    this.dataSource.paginator = this.paginator;
  }

  cambioPagina(e: any) {
      this.Iniciar(e.pageIndex);
  }

  Iniciar(page: number): void {
    this.aSer.traerArchivosNucleo(this.seleccionado.nucleo_consulta.id, page).subscribe(res => {
      this.dataSource = new MatTableDataSource(res.content);
      this.cantidad = res.totalElements;
      this.paginator._intl.itemsPerPageLabel = 'Registros por pagina';
      this.paginator._intl.nextPageLabel = 'Siguiente';
      this.paginator._intl.previousPageLabel = 'Anterior';
    });
    this.cSer.insertarConsulta(this.seleccionado).subscribe();
  }

  traerNucleos() {
    this.nSer.traerNucleosAll().subscribe(n => this.nucleos = n as Nucleotematico[]);
  }
}
