import { Archivo } from './archivo';
import { Consultas } from './consultas';
import { Persona } from './persona';
export class Nucleotematico{
  id: number;
  persona: Array<Persona> = [];
  archivo: Array<Archivo> = [];
  consulta: Array<Consultas> = [];
  nucleo: string;
}