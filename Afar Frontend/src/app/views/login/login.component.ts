import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../user.service';
import { Router } from '@angular/router';
import { AppUser } from '../../AppUser';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit {

  errMsg = ""
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {

    let formControls = {
      email: new FormControl('', [
        Validators.required,
        Validators.email
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6)
      ])
    };

    this.loginForm = this.fb.group(formControls);
  }

  get email() { return this.loginForm.get('email') };
  get password() { return this.loginForm.get('password') };


  ngOnInit(): void {

    const isLoggedIn = this.userService.isLoggedIn();

    if (isLoggedIn) {
      console.log('logged ');
    }
  }

  login() {
    const data = this.loginForm.value;

    const user = new AppUser(data.email, data.password);

    this.userService.loging(data.email, data.password).subscribe(
      res => {
        console.log(res);
        
        if (res === true) {
          console.log('role men login=>' + this.userService.isRole());
          this.router.navigate(['dashboard']);
        }

      },
      err => {
        console.log(err);
        this.errMsg = "Veuillez vérifier votre email et mot de passe"
      }
    );




  }
}
