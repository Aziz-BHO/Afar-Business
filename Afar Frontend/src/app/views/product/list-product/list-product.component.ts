import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ProductService } from '../product.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Product } from '../product.model';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.scss']
})
export class ListProductComponent implements OnInit {
  @ViewChild('delModal') public delModal: ModalDirective;
  @ViewChild('photoModal') public photoModal: ModalDirective;
  @ViewChild('myModal') public myModal: ModalDirective;

  selectedProduct: any;
  products: any[];
  productForm: FormGroup;
  constructor(
    private router: Router,
    private productService: ProductService,
    private fb: FormBuilder,
    public jwtHelper: JwtHelperService
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
    this.productForm = this.fb.group(formControls);
  }

  ngOnInit() {
    this.getAllProducts();

  }
  get name() { return this.productForm.get('name'); }
  get desc() { return this.productForm.get('desc'); }
  get price() { return this.productForm.get('price'); }
  get discountPrice() { return this.productForm.get('discountPrice'); }
  get discountPoints() { return this.productForm.get('discountPoints'); }

  getAllProducts() {
    this.productService.getProducts().subscribe(
      (res: any) => {
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let token = currentUser && currentUser.token;
        let id = this.jwtHelper.decodeToken(token).User_Id;

        let prods = res.filter(r => r.entreprise.id == id)

        this.products = prods
      }
    );
  }

  getSelectedData(selectedData: any) {
    console.log(selectedData);
    this.selectedProduct = selectedData;
  }

  deleteProduct() {
    this.productService.deleteProduct(this.selectedProduct.id).subscribe(res => {
      this.router.navigate(['/products/list'], { skipLocationChange: true }).then(() => {
        this.getAllProducts();
        this.delModal.hide();
      });
    });
  }

  uploadImage(product: any) {
    this.router.navigate(['/products/uploadphoto', product.id]);
  }

  update() {
    let data = this.productForm.value;

    const product = new Product(this.selectedProduct.id, data.name, data.price, data.desc, data.discountPrice, data.discountPoints);

    this.productService.updateProduct(product).subscribe(res => {
      this.router.navigate(['/products/list'], { skipLocationChange: true }).then(() => {
        this.getAllProducts();
        this.myModal.hide();
      });
    });
  }
}
