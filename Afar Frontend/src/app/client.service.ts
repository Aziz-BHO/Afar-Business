import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Client} from './client.model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private getCategory = 'http://127.0.0.1:8080/client';
  private getOnecategoryUrl = 'https://backend-people-crud-app.herokuapp.com/categorys/';
  private deletecategoryUrl = 'http://127.0.0.1:8080/client/';
  private addcategoryUrl = 'http://127.0.0.1:8080/client';
  private updatecategoryUrl = 'http://127.0.0.1:8080/client';

  private registercategoryUrl = 'http://127.0.0.1:8080/entreprise/register/client/';
  private logincategoryUrl = 'http://127.0.0.1:8080/login';
  private loggedIn: boolean;
  private token: string;
  private role: any;
  private sub: string;
  constructor(private http: HttpClient) { }


  getAllclients() {
    return this.http.get<any>(this.getCategory );
  }

  getOneClient(id: String) {
    return this.http.get<any>(this.getOnecategoryUrl + id);
  }

  deleteClient(id: String) {
    return this.http.delete<any>(this.deletecategoryUrl + id);
  }

  addClient(client: Client) {
    return this.http.post<any>(this.addcategoryUrl, client);
  }

  updateClient(client: Client) {
    return this.http.put<any>(this.updatecategoryUrl, client);
  }
}
