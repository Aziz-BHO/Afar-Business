import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ListProductComponent } from './list-product/list-product.component';
import { AddProductComponent } from './add-product/add-product.component';
import { PhotoUploaderComponent } from './add-product/photo-uploader/photo-uploader.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Product'
    },
    children: [
      {
        path: '',
        redirectTo: 'list'
      },
      {
        path: 'add',
        component: AddProductComponent,
        data: {
          title: 'Add Product'
        }
      },
      {
        path: 'uploadphoto/:pId',
        component: PhotoUploaderComponent,
        data: {
          title: 'Upload Photo'
        }
      },
      {
        path: 'list',
        component: ListProductComponent,
        data: {
          title: 'Products List'
        }
      }
    ]
  }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
