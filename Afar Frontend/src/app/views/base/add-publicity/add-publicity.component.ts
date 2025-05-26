import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';
import { UserService } from '../../../user.service';
import { Publicity } from '../publicity.model';
import { PublicityService } from '../publicity.service';

@Component({
  selector: 'app-add-publicity',
  templateUrl: './add-publicity.component.html',
  styleUrls: ['./add-publicity.component.scss']
})
export class AddPublicityComponent implements OnInit {
  submitForm: FormGroup;
  entrepriseId: number;
  publicityId: string;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    private userService: UserService,
    private publicityService: PublicityService,
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
  get points() { return this.submitForm.get('points'); }

  ngOnInit(): void {
    let token = this.userService.getToken();
    const jwtHelper = new JwtHelperService();
    this.entrepriseId = jwtHelper.decodeToken(token).User_Id;
  }

  submit() {
    let data = this.submitForm.value;

    const pub = new Publicity(null, data.name, data.desc, data.points);

    this.publicityService.addPublicity(this.entrepriseId, pub).subscribe(
      (res: any) => {
        console.log(res);
        this.router.navigate(['/publicities/uploadvideo', res.id]);
        this.toastr.success('Publicity added with success');
      },
      (err) => {
        this.toastr.error('Error when adding publicity');
      }
    );
  }

}
