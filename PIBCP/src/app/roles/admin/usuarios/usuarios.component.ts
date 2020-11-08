import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Persona } from '../../../models/persona';
import { MatPaginator } from '@angular/material/paginator';
import { PersonaServiceService } from '../../../services/persona-service.service';
import { Rol } from 'src/app/models/rol';
import { RolServiceService } from '../../../services/rol-service.service';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  
  cantidad = 0;
  seleccionado = 1;
  roles: Array<Rol> = [];
  displayedColumns: string[] = ['id','avatar','nombre','correo','rol'];
  dataSource = new MatTableDataSource<Persona>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  
  constructor(private pSer: PersonaServiceService, private rSer: RolServiceService) { }

  ngOnInit(): void {
    this.traerUsers(0);
    this.cargarPaginador();
    this.cargarRoles();
  }

  cargarPaginador() {
    this.dataSource.paginator = this.paginator;
  }

  cambioPagina(e: any) {
      this.traerUsers(e.pageIndex);
  }

  traerUsers(page: number) {
    this.pSer.usuariosXRolPage(this.seleccionado, page).subscribe(data => {
      console.log(data);
      this.dataSource = new MatTableDataSource(data.content);
      this.cantidad = data.totalElements;
      this.paginator._intl.itemsPerPageLabel = 'Registros por pagina';
      this.paginator._intl.nextPageLabel = 'Siguiente';
      this.paginator._intl.previousPageLabel = 'Anterior';
    });
  }

  cargarRoles() {
    this.rSer.traerRoles().subscribe(r => this.roles = r as Rol[]);
  }

}
