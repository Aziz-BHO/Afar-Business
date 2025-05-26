import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { PublicityService } from '../publicity.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Publicity} from '../publicity.model';
import {ToastrService} from 'ngx-toastr';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-publicites-list',
  templateUrl: './publicites-list.component.html',
  styleUrls: ['./publicites-list.component.scss']
})
export class PublicitesListComponent implements OnInit {
  @ViewChild('delModal') public delModal: ModalDirective;
  @ViewChild('myModal') public myModal: ModalDirective;
  selectedPublicity: any;
  publicities: any[];
  submitForm: FormGroup ;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    private publicityService: PublicityService,
    public jwtHelper: JwtHelperService
  ) {
    const formControls = {
      name: new FormControl('', [
        Validators.required,
        Validators.minLength(2)
      ]),
      desc: new FormControl('', [
        Validators.required
      ]),
      points: new FormControl('', [
        Validators.required,
        Validators.min(10)
      ])
    };
    this.submitForm = this.fb.group(formControls);
  }
  get name() { return this.submitForm.get('name'); }
  get desc() { return this.submitForm.get('desc'); }
  get points() { return this.submitForm.get('points');}
  ngOnInit() {
    this.getAllPublicities();
  }

  getAllPublicities() {
    this.publicityService.getPublicities().subscribe(
      (res: any) => {
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let token = currentUser && currentUser.token;
        let id = this.jwtHelper.decodeToken(token).User_Id;

        let pubs = res.filter(r => r.entreprise.id == id)
        this.publicities = pubs;
      }
    );
  }

  getSelectedData(selectedData: any) {
    console.log(selectedData);
    this.selectedPublicity = selectedData ;
  }

  deletePublicity() {
    this.publicityService.deletePublicity(this.selectedPublicity.id).subscribe(res => {
      this.router.navigate(['/publicities/list'],{skipLocationChange:true}).then(()=>{
        this.getAllPublicities();
        this.delModal.hide() ;
      });
    });
  }
  submit() {
    let data = this.submitForm.value;

    const pub = new Publicity(this.selectedPublicity.id,data.name, data.desc, data.points);

    this.publicityService.updatePublicity( pub).subscribe(
      (res: any) => {
        console.log(res);
        this.router.navigate(['/publicities/list'],{skipLocationChange:true}).then(()=>{
          this.getAllPublicities();
          this.myModal.hide() ;
        });
        this.toastr.success('Publicity added with success');
      },
      (err) => {
        this.toastr.error('Error when adding publicity');
      }
    );
  }
  uploadImage(publicity: any) {
    this.router.navigate(['/publicities/uploadvideo', publicity.id]);
  }
}
