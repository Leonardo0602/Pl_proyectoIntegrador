import { Persona } from './persona';
import { Nucleotematico } from './nucleo';
export class Archivo{
  id: number;
  persona_archivo: Persona;
  nucleo_archivo: Nucleotematico;
  fecha: Date;
  url: string;
  titulo: string;
}