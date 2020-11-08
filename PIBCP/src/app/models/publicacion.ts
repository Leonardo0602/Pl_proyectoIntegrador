import { TipoComentario } from './tipocomentario';
import { Persona } from './persona';
export class Publicacion{
  id: number;
  fecha: Date;
  asunto: string;
  reacciones: number;
  tipo: TipoComentario;
  persona_publicacion: Persona;
}