import { Archivo } from './archivo';
import { Calificacion } from './calificacion';
import { Nucleotematico } from './nucleo';
import { Publicacion } from './publicacion';
import { Rol } from './rol';
export class Persona{
  id: number;
  nombre: string;
  apellido: string;
  documento: number;
  correo: string;
  clave: string;
  url_foto: string;
  total: number;
  rol: Rol;
  nucleo: Array<Nucleotematico> = [];
  archivo: Array<Archivo> = [];
  publicacion: Array<Publicacion> = [];
  calificacion: Array<Calificacion> = [];
}