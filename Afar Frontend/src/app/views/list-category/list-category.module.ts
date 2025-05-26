import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AddCategoryComponent} from '../add-category/add-category.component';
import {ListCategoryComponent} from './list-category.component';
import {ModalModule} from 'ngx-bootstrap/modal';



const routes: Routes = [
  {
    path: '',
    component: ListCategoryComponent,
    data: {
      title: 'List-Category'
    }
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes), ModalModule.forRoot()],
  exports: [RouterModule]
})
export class ListCategoryModule { }
