import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { AppUser } from './AppUser';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private getAllUsersUrl = 'https://backend-people-crud-app.herokuapp.com/users';
  private getOneUserUrl = 'https://backend-people-crud-app.herokuapp.com/users/';
  private deleteUserUrl = 'https://backend-people-crud-app.herokuapp.com/users/';
  private addUserUrl = 'https://backend-people-crud-app.herokuapp.com/users/add';
  private updateUserUrl = 'https://backend-people-crud-app.herokuapp.com/users/update';

  private registerUserUrl = 'http://127.0.0.1:8080/register';
  private loginUserUrl = 'http://127.0.0.1:8080/login';
  private loggedIn: boolean;
  token: string;
  private role: any;
  private sub: string;
  ID: String;
  constructor(private http: HttpClient, public jwtHelper: JwtHelperService) {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;

  }
  contentHeader = new HttpHeaders({ 'Content-Type': 'application/json' });
  options: {
    headers?: HttpHeaders | { [header: string]: string | string[] },
    observe?: 'body' | 'events' | 'response',
    params?: HttpParams | { [param: string]: string | string[] },
    reportProgress?: boolean,
    responseType?: 'arraybuffer' | 'blob' | 'json' | 'text',
    withCredentials?: boolean,
  };

  getAllUsers() {
    return this.http.get<any>(this.getAllUsersUrl);
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

  // Register & Login

  registerAdmin(user: AppUser) {
    return this.http.post<any>(this.registerUserUrl, user);
  }


  loginAdmin(user: AppUser) {
    return this.http.post<any>(this.loginUserUrl, user);
  }

  isLoggedIn() {

    const token = localStorage.getItem('currentUser');

    if (token) {
      return true;
    } else {
      return false;
    }
  }
  getToken() {
    return this.token;
  }

  loging(email: string, password: string): Observable<boolean> {
    return this.http.post(this.loginUserUrl, { email: email, password: password }, { observe: 'response' })
      .pipe(map((response: HttpResponse<any>) => {
        console.log(response);
        const token = response.headers.get('Authorization');
        console.log(token);
        this.loggedIn = true;

        // login successful if there's a jwt token in the response
        // let token = response.json() && response.json().token;
        if (token) {
          // set token property
          this.token = token;

          const jwtHelper = new JwtHelperService();

          this.role = jwtHelper.decodeToken(this.token).roles;
          this.ID = jwtHelper.decodeToken(this.token).User_Id;
          console.log(jwtHelper.decodeToken(this.token).roles);
          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify({ ID: this.ID, email: email, token: token }));
          // return true to indicate successful login
          return true;
        } else {
          // return false to indicate failed login
          return false;
        }
      }));
  }

  logout(): void {
    this.token = null;
    localStorage.removeItem('currentUser');
  }

  isPseudo() {

    this.sub = this.jwtHelper.decodeToken(this.token).sub;
    console.log('nom *************' + this.sub);
    return this.sub;

  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('currentUser');
    console.log('isauth auth.service' + this.token);
    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }

  isRole() {
    const jwtHelper = new JwtHelperService();
    return this.role = jwtHelper.decodeToken(this.token).roles;

  }
  isID() {
    const jwtHelper = new JwtHelperService();
    return this.role = jwtHelper.decodeToken(this.token).User_ID;

  }

  getUserLoggedIn() {
    if (this.token) {
      return true;
    }

    return false;
  }
}
