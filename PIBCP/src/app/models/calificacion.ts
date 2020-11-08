import { Persona } from './persona';
export class Calificacion{
  id: number;
  persona_profesor: Persona;
  id_alumno: number;
  cantidad: number;
  voto: boolean;
}