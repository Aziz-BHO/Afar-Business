import { Component, OnInit } from '@angular/core';
import { AdminnavItems } from '../../_nav';
import { FormBuilder } from '@angular/forms';
import { UserService } from '../../user.service';
import { Router } from '@angular/router';
import { EntreprisenavItems } from '../../_navEntreprise';
import { JwtHelperService } from '@auth0/angular-jwt';
import { EntrepriseServiceService } from '../../entreprise-service.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnInit {

  public sidebarMinimized = false;

  public navItems = null;
  public isAdmin: Boolean;
  private Id: any;
  private entreprise: any;
  private token: string;


  constructor(
    private userService: UserService,
    public jwtHelper: JwtHelperService,
    private entrepriseService: EntrepriseServiceService,

  ) {
    
    console.log(userService.isRole());
    if (userService.isRole() === 'admin') {
      this.navItems = AdminnavItems;
      this.isAdmin = true
    } else {
      this.navItems = EntreprisenavItems;
      this.isAdmin = false
    }


  }

  logout() {
    this.userService.logout();
  }

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }

  ngOnInit(): void {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
    this.Id = this.jwtHelper.decodeToken(this.token).User_Id;
    this.entrepriseService.getOneUser(this.Id).subscribe(res => {
      this.entreprise = res;
    });
  }
}
