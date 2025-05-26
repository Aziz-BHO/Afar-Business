import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';
import { UserService } from '../../../user.service';
import { Product } from '../product.model';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent implements OnInit {
  submitForm: FormGroup;
  entrepriseId: number;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    private userService: UserService,
    private productService: ProductService
  ) {
    const formControls = {
      name: new FormControl('', [
        Validators.required,
        Validators.minLength(2)
      ]),
      price: new FormControl('', [
        Validators.required,
        Validators.min(10)
      ]),
      desc: new FormControl('', [
        Validators.required
      ]),
      discountPrice: new FormControl('', [
        Validators.required,
        Validators.min(10)
      ]),
      discountPoints: new FormControl('', [
        Validators.required,
        Validators.min(10)
      ])
    };

    this.submitForm = this.fb.group(formControls);
  }

  get name() { return this.submitForm.get('name'); }
  get desc() { return this.submitForm.get('desc'); }
  get price() { return this.submitForm.get('price'); }
  get discountPrice() { return this.submitForm.get('discountPrice'); }
  get discountPoints() { return this.submitForm.get('discountPoints'); }

  ngOnInit(): void {
    let token = this.userService.getToken();
    const jwtHelper = new JwtHelperService();
    this.entrepriseId = jwtHelper.decodeToken(token).User_Id;
  }

  submit() {
    let data = this.submitForm.value;

    const product = new Product(null, data.name, data.price, data.desc, data.discountPrice, data.discountPoints);
    console.log(this.entrepriseId);

    this.productService.addProduct(this.entrepriseId, product).subscribe(
      (res: any) => {
        console.log(res);
        this.router.navigate(['/products/uploadphoto', res.id]);
        this.toastr.success('Product added with success');
      },
      (err) => {
        this.toastr.error('Error when adding publicity');
      }
    );
  }
}
