
export class FileItem{
   public archivo: File;
   public url: string;
   public cargando:boolean;
   public progreso: number = 0;
   public nombre: string;

   constructor(archivo:File){
      this.archivo = archivo;
      this.cargando = false;
      this.progreso = 0;
      this.nombre = archivo.name;
   }
}