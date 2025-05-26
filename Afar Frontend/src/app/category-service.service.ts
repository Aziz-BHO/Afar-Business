import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Category} from './category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  private getCategory = 'http://127.0.0.1:8080/category';
  private getOnecategoryUrl = 'https://backend-people-crud-app.herokuapp.com/categorys/';
  private deletecategoryUrl = 'http://127.0.0.1:8080/category/';
  private addcategoryUrl = 'http://127.0.0.1:8080/category';
  private updatecategoryUrl = 'http://127.0.0.1:8080/category';

  private registercategoryUrl = 'http://127.0.0.1:8080/entreprise/register/category/';
  private logincategoryUrl = 'http://127.0.0.1:8080/login';
  private loggedIn: boolean;
  private token: string;
  private role: any;
  private sub: string;
  constructor(private http: HttpClient) { }


  getAllcategories() {
    return this.http.get<any>(this.getCategory );
  }

  getOneCategory(id: String) {
    return this.http.get<any>(this.getOnecategoryUrl + id);
  }

  deleteCategory(id: String) {
    return this.http.delete<any>(this.deletecategoryUrl + id);
  }

  addCategory(category: Category) {
    return this.http.post<any>(this.addcategoryUrl, category);
  }

  updateCategory(category: Category) {
    return this.http.put<any>(this.updatecategoryUrl, category);
  }

}
