import { Pipe, PipeTransform } from '@angular/core';
import { Calificacion } from '../../models/calificacion';

@Pipe({
  name: 'calificacionPipe'
})
export class CalificacionPipePipe implements PipeTransform {
  uno = [];
  dos = [];
  tres = [];
  cuatro = [];
  cinco = [];
  total: number = 0.;
  totale = 5;

  transform(value: Array<Calificacion>, ...args: unknown[]): number {
    
    if (value != null) {
        value.forEach(f => {
          if (f.cantidad === 1) {
            this.uno.push(f);
          }
    
          if (f.cantidad === 2) {
            this.dos.push(f);
          }
    
          if (f.cantidad === 3) {
            this.tres.push(f);
          }
    
          if (f.cantidad === 4) {
            this.cuatro.push(f);
          }
    
          if (f.cantidad === 5) {
            this.cinco.push(f);
          }
    
        });
      
        const total1L = this.uno.length;
        const total2L = this.dos.length;
        const total3L = this.tres.length;
        const total4L = this.cuatro.length;
        const total5L = this.cinco.length;
    
    
        const total1 = 1 * total1L;
        const total2 = 2 * total2L;
        const total3 = 3 * total3L;
        const total4 = 4 * total4L;
        const total5 = 5 * total5L;
    
        const sum = total1L + total2L + total3L + total4L + total5L;
        this.total = (total1 + total2 + total3 + total4 + total5) / sum;
    }

    return this.total;
  }

}
