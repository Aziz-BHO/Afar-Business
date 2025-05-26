import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Publicity } from './publicity.model';

@Injectable({
  providedIn: 'root'
})
export class PublicityService {
  private uploadUrl = 'http://127.0.0.1:8080/uploadpublicity/';
  private publicityUrl = 'http://127.0.0.1:8080/publicity';

  constructor(private http: HttpClient) { }

  getPublicities() {
    return this.http.get<any>(this.publicityUrl);
  }

  addPublicity(idEntreprise: number, publicity: Publicity) {
    return this.http.post<any>(this.publicityUrl + '/entreprise/' + idEntreprise, publicity);
  }
  updatePublicity( publicity: Publicity) {
    return this.http.put<any>(this.publicityUrl  , publicity);
  }

  deletePublicity(idPublicity) {
    return this.http.delete<any>(this.publicityUrl + '/' + idPublicity);
  }

  uploadFile(file: File, idPublicity) {
    const formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('POST', this.uploadUrl + idPublicity, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

}
