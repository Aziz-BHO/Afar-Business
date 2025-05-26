import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../user.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Entreprise} from '../../entreprise.model';
import {EntrepriseServiceService} from '../../entreprise-service.service';
import {CategoryServiceService} from '../../category-service.service';
import {Category} from '../../category.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'register.component.html'
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup ;
  categories: Category[] ;
  constructor(
    private fb: FormBuilder,
    private userService: EntrepriseServiceService,
    private router: Router,
    private toastr: ToastrService,
    private categoryService: CategoryServiceService ,

  ) {


    const formControls = {
      name: new FormControl('', [
        Validators.required,
        Validators.pattern('[A-Za-z .\'-]+'),
        Validators.minLength(2)
      ]),
      phone: new FormControl('', [
        Validators.required,
        Validators.pattern('[0-9]+'),
        Validators.minLength(8),
        Validators.maxLength(13)
      ]),
      email: new FormControl('', [
        Validators.required,
        Validators.email
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6)
      ]),
      repassword: new FormControl('', [
        Validators.required,
      ]),
      category: new FormControl('', [
        Validators.required,
      ])
    };

    this.registerForm = this.fb.group(formControls);
  }
  get name() { return this.registerForm.get('name'); }
  get phone() { return this.registerForm.get('phone'); }
  get email() { return this.registerForm.get('email'); }
  get password() { return this.registerForm.get('password'); }
  get repassword() { return this.registerForm.get('repassword'); }
  get category() { return this.registerForm.get('category'); }

  ngOnInit(): void {

    this.categoryService.getAllcategories().subscribe(data => {
      this.categories = data ;
    });


  }



  register() {

    const data = this.registerForm.value;
    const category = this.category.value ;

    console.log(category.id) ;

    const entreprise = new Entreprise(data.name, data.email,data.password, data.phone );

    this.userService.registerEntreprise(entreprise, category.id).subscribe(
      res => {
        this.toastr.success(res.message);
        this.router.navigate(['/login']);
      },
      err => {
        console.log(err);
      }
    );

  }
}
