import { Injectable } from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {AppUser} from './AppUser';
import {Entreprise} from './entreprise.model';

@Injectable({
  providedIn: 'root'
})
export class EntrepriseServiceService {

  private getAllUsersUrl = 'http://127.0.0.1:8080/entreprise';
  private entrepriseuploadPhoto = 'http://127.0.0.1:8080/uploadimage/';
  private getAllCommandsUrl = 'http://127.0.0.1:8080/command';
  private confirmCommand = 'http://127.0.0.1:8080/command/confirm/';
  private cancelCommand = 'http://127.0.0.1:8080/command/cancel/';
  private getAllRequestUrl = 'http://127.0.0.1:8080/request';
  private validateAllRequestUrl = 'http://127.0.0.1:8080/request/validate/';
  private cancelAllRequestUrl = 'http://127.0.0.1:8080/request/cancel/';
  private getOneUserUrl = 'http://127.0.0.1:8080/entreprise/';
  private deleteUserUrl = 'http://127.0.0.1:8080/entreprise/';
  private addUserUrl = 'https://backend-people-crud-app.herokuapp.com/users/add';
  private updateUserUrl = 'https://backend-people-crud-app.herokuapp.com/users/update';

  private registerUserUrl = 'http://127.0.0.1:8080/entreprise/register/category/';
  private ActivateUrl = 'http://127.0.0.1:8080/entreprise/activate/';
  private DesactivateUrl = 'http://127.0.0.1:8080/entreprise/desactivate/';
  private loginUserUrl = 'http://127.0.0.1:8080/login';
  private requestEntrepriseUrl = 'http://127.0.0.1:8080/request/entreprise/';
  private loggedIn: boolean;
  private token: string;
  private role: any;
  private sub: string;

  constructor(private http: HttpClient) { }

  getAllUsers() {
    return this.http.get<any>(this.getAllUsersUrl);
  }
  getAllCommands() {
    return this.http.get<any>(this.getAllCommandsUrl);
  }
  getAllRequests() {
    return this.http.get<any>(this.getAllRequestUrl);
  }

  getOneUser(id: String) {
    return this.http.get<any>(this.getOneUserUrl + id);
  }

  deleteUser(id: String) {
    return this.http.delete<any>(this.deleteUserUrl + id);
  }

  addUser(user: AppUser) {
    return this.http.post<any>(this.addUserUrl, user);
  }

  updateUser(user: AppUser) {
    return this.http.put<any>(this.updateUserUrl, user);
  }

  activateEntreprise(id: String) {
    // @ts-ignore
    return this.http.put<any>(this.ActivateUrl + id  );
  }

  DesactivateEntreprise(id: String) {
    // @ts-ignore
    return this.http.put<any>(this.DesactivateUrl + id  );
  }

  // Register & Login

  registerEntreprise(user: Entreprise , id: String ) {
    return this.http.post<any>(this.registerUserUrl + id, user);
  }
  demandeEntreprise(user: Entreprise , id: String ) {
    return this.http.post<any>(this.requestEntrepriseUrl + id, user);
  }
  demandeEntrepriseValidate( id: String ) {
    // @ts-ignore
    return this.http.put<any>(this.validateAllRequestUrl + id);
  }
  demandeEntrepriseCancel( id: String ) {
    // @ts-ignore
    return this.http.put<any>(this.cancelAllRequestUrl + id);
  }
  validateCommand( id: String ) {
    // @ts-ignore
    return this.http.put<any>(this.confirmCommand + id);
  }

  cancelcommand( id: String ) {
    // @ts-ignore
    return this.http.put<any>(this.cancelCommand + id);
  }
  uploadFile(file: File, idEntreprise) {
    const formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('POST', this.entrepriseuploadPhoto + idEntreprise, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }
}
