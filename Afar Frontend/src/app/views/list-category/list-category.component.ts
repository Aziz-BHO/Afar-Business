import {Component, OnInit, ViewChild} from '@angular/core';
import {CategoryServiceService} from '../../category-service.service';
import {Router} from '@angular/router';
import {Category} from '../../category.model';
import {ModalDirective} from 'ngx-bootstrap/modal';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
@Component({
  selector: 'app-list-category',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.scss']
})
export class ListCategoryComponent implements OnInit {
  @ViewChild('myModal') public myModal: ModalDirective;
  @ViewChild('delModal') public delModal: ModalDirective;
  categories: Category[] ;
  modalRef: BsModalRef;
  selectedCategory: Category ;
  categoryForm: FormGroup ;
  ID: String ;
  constructor( private categoryService: CategoryServiceService ,
               private router: Router ,
               private modalService: BsModalService,
               private toastr: ToastrService,
               private fb: FormBuilder,
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
    this.categoryService.getAllcategories().subscribe(data => {
      this.categories = data ;
    });
  }


  getSelectedData(selectedData: Category): void {

    console.log(selectedData);
    this.selectedCategory = selectedData ;
  }
  update() {

    const data = this.categoryForm.value;
    const category = new Category(  data.name , this.selectedCategory.id );
    this.categoryService.updateCategory(category).subscribe(res => {
        this.toastr.success(res.message);
        this.router.navigate(['/List-Category'],{skipLocationChange:true}).then(()=>{
          this.ngOnInit();
          this.myModal.hide() ;
        }) ;
      },
      err => {
        console.log(err);
      }
    );
  }
  delete() {
    this.categoryService.deleteCategory(this.selectedCategory.id).subscribe(res => {

        this.router.navigate(['/List-Category'],{skipLocationChange:true}).then(()=>{
          this.ngOnInit();
          this.delModal.hide() ;
        }) ;
      },
      err => {
        console.log(err);
      }
    );
  }
}
