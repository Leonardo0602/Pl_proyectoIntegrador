import { Injectable } from '@angular/core';
import { AngularFireStorage } from '@angular/fire/storage';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FireStorageServiceService {
  public carpeta_imagenes = 'docs';
  constructor(private storage: AngularFireStorage, private http: HttpClient) { }

  public tareaCloudStorage(nombreArchivo: string, datos: File) {
    return this.storage.upload(`${this.carpeta_imagenes}/${nombreArchivo}`, datos);
  }

  public referenciaCloudStorage(nombreArchivo: string) {
    return this.storage.ref(`${this.carpeta_imagenes}/${nombreArchivo}`);
  }

  /*
  
  subirFireBase(item: FileItem, archivo: Archivo) {
    this.storage.ref();
    const storageRef = firebase.storage().ref();
    item.cargando = true;
    const uploadTask : firebase.storage.UploadTask = storageRef.child(`${this.carpeta_imagenes}/${item.nombre}`)
      .put(item.archivo);
      uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
        (snapshot: firebase.storage.UploadTaskSnapshot) => item.progreso =
        (snapshot.bytesTransferred / snapshot.totalBytes)*100,
        (error) => console.error('error al subir', error),
        () => {

          console.log('imagen cargada correctamente');
          uploadTask.snapshot.ref.getDownloadURL().then(downloadURL => {
          archivo.url = downloadURL;
          this.subirArchivo(archivo).subscribe(response =>console.log(response));
          });

        })
        ;
  }


  subirArchivo(archivo: Archivo): Observable<Archivo> {
    return this.http.post<Archivo>(`${environment.URL_EP}/archivos/subir`, archivo);
  }
  */


  
}
