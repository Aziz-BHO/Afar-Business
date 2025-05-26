import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Category } from '../../category.model';
import { EntrepriseServiceService } from '../../entreprise-service.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Entreprise } from '../../entreprise.model';
import { UserService } from '../../user.service';
import { HttpClient, HttpEventType, HttpResponse } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-request-edit-entreprise',
  templateUrl: './request-edit-entreprise.component.html',
  styleUrls: ['./request-edit-entreprise.component.scss']
})
export class RequestEditEntrepriseComponent implements OnInit {
  registerForm: FormGroup;
  categories: Category[];
  private loggedIn: boolean;
  token: string;
  Id: String;
  entreprise: Entreprise;
  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };
  url;
  format;
  constructor(
    private fb: FormBuilder,
    private entrepriseService: EntrepriseServiceService,
    private router: Router,
    private toastr: ToastrService,
    private userService: UserService,
    public jwtHelper: JwtHelperService
  ) {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
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
      adress: new FormControl('', [
        Validators.required,
        // Validators.email
      ]),
    };
    this.registerForm = this.fb.group(formControls);
  }

  get name() { return this.registerForm.get('name'); }
  get phone() { return this.registerForm.get('phone'); }
  get email() { return this.registerForm.get('email'); }
  get adress() { return this.registerForm.get('adress'); }
  ngOnInit(): void {
    this.Id = this.jwtHelper.decodeToken(this.token).User_Id;
    this.entrepriseService.getOneUser(this.Id).subscribe(res => {
      this.entreprise = res;
      this.registerForm.patchValue(this.entreprise);

    });

  }
  demander() {

    const data = this.registerForm.value;
    const password = this.entreprise.password;
    const status = this.entreprise.status;
    const entreprise = new Entreprise(data.name, data.email, password, data.phone, status, data.adress, this.Id);

    this.entrepriseService.demandeEntreprise(entreprise, this.Id).subscribe(
      res => {
        this.toastr.success(res.message);
        this.router.navigate(['/dashboard']);
      },
      err => {
        console.log(err);
      }
    );

  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.entrepriseService.uploadFile(this.currentFileUpload, this.Id).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        this.router.navigate(['/dashboard']);
      }
    });

    this.selectedFiles = undefined;
  }

  onSelectFile(event) {
    this.selectedFiles = event.target.files;
    const file = event.target.files && event.target.files[0];
    if (file) {
      var reader = new FileReader();
      reader.readAsDataURL(file);
      if (file.type.indexOf('image') > -1) {
        this.format = 'image';
      } else if (file.type.indexOf('video') > -1) {
        this.format = 'video';
      }
      reader.onload = (event) => {
        this.url = (<FileReader>event.target).result;
      }
    }
  }
}
