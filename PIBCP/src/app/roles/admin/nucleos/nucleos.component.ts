import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Nucleotematico } from '../../../models/nucleo';
import { NucleoTematicoService } from '../../../services/nucleo-tematico.service';
import { MatPaginator } from '@angular/material/paginator';
import swal from 'sweetalert2';

@Component({
  selector: 'app-nucleos',
  templateUrl: './nucleos.component.html',
  styleUrls: ['./nucleos.component.css']
})
export class NucleosComponent implements OnInit {

  nucleoReg: FormGroup;
  nucleo: Nucleotematico = new Nucleotematico();
  stringRegx = /^[ A-Za-z]+$/;
  displayedColumns: string[] = ['id','nucleo'];
  dataSource = new MatTableDataSource<Nucleotematico>();
  cantidad = 0;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private fb: FormBuilder, private nSer: NucleoTematicoService) { }

  ngOnInit(): void {
    this.validarNuc();
    this.traerNucleos(0);
    this.cargarPaginador();
  }

  validarNuc() {
    this.nucleoReg = this.fb.group({
      nucleo: new FormControl(this.nucleo.nucleo, [Validators.required, Validators.pattern(this.stringRegx)])
    })
  }

  cargarPaginador() {
    this.dataSource.paginator = this.paginator;
  }

  cambioPagina(e: any) {
      this.traerNucleos(e.pageIndex);
  }

  traerNucleos(page: number) {
    this.nSer.traerNucleosAllPage(page).subscribe(data => {
      console.log(data);
      this.dataSource = new MatTableDataSource(data.content);
      this.cantidad = data.totalElements;
      this.paginator._intl.itemsPerPageLabel = 'Registros por pagina';
      this.paginator._intl.nextPageLabel = 'Siguiente';
      this.paginator._intl.previousPageLabel = 'Anterior';
    });
  }

  guardar() {
    this.nSer.guardarNucleo(this.nucleoReg.value).subscribe(p => {
      swal.fire('Nucleo guardado correctamente', `${p.nucleo}`, 'success');
      this.traerNucleos(0);
      this.nucleoReg.reset();
    })
  }
}
