import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {CategoryServiceService} from '../../category-service.service';
import {Category} from '../../category.model';
import {HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.scss']
})
export class AddCategoryComponent implements OnInit {
  categoryForm: FormGroup ;
  ID: String ;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService,

    private categoryService: CategoryServiceService ,
  ) {
    const formControls = {
      name: new FormControl('', [
        Validators.required
      ])
    };
    this.categoryForm = this.fb.group(formControls);
  }
  get name() { return this.categoryForm.get('name'); }
  ngOnInit(): void {


  }

  save() {

    const data = this.categoryForm.value;
    const category = new Category(data.name);
    this.categoryService.addCategory(category).subscribe(
      res => {
      this.toastr.success(res.message);
      this.router.navigate(['/List-Category']) ;
    },
      err => {
        console.log(err);
      }
      );
  }

}
