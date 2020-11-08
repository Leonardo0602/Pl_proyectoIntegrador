import { Component, OnInit } from '@angular/core';
import { PersonaServiceService } from '../../../services/persona-service.service';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';
import { ConsultaServiceService } from '../../../services/consulta-service.service';
import { ConsultasindiceDTO } from '../../../models/consultasindicedto';
import { IPersonaBestDTO } from '../../../models/ipersonabestdto';

@Component({
  selector: 'app-popularidad',
  templateUrl: './popularidad.component.html',
  styleUrls: ['./popularidad.component.css']
})
export class PopularidadComponent implements OnInit {


  
  nucleos:boolean;
  profesores: boolean;
  charts: Array<ConsultasindiceDTO> = [];
  profes: Array<IPersonaBestDTO> = [];
  barLabels: Array<Label> = [];
  barData: Array<ChartDataSets> = [];
  numbers: Array<number> = [];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  stars = 5;
  barChartOptions: ChartOptions = {
    responsive: true,
  };

  constructor(private cSer: ConsultaServiceService, private pSer: PersonaServiceService) { }

  ngOnInit(): void {
    this.nucleos = true;
    this.profesores = true;
    this.cSer.obtenerConsultas().subscribe(p => {
      this.charts = p as ConsultasindiceDTO[],
        p.forEach(pe => {
          this.barLabels.push(pe.nucleo),
            this.numbers.push(pe.total)
        });
    
      this.barData = [{ data: this.numbers, label: 'Nucleos Mas Buscados', backgroundColor: "rgba(0,0,0,0.9)", hoverBackgroundColor: "rgba(55,55,55,0.9)"}]
    });

    this.pSer.bestPersonas().subscribe(p => this.profes = p as IPersonaBestDTO[]);
  }

  activarNucleos() {
    this.nucleos = false;
    this.profesores = true;
    
  }

  activarProfesores() {
    this.nucleos = true;
    this.profesores = false;
  }


  

  
}
