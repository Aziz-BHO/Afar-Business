import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ListCategoryComponent} from '../list-category/list-category.component';
import {ModalModule} from 'ngx-bootstrap/modal';
import {ListClientComponent} from './list-client.component';


const routes: Routes = [
  {
    path: '',
    component: ListClientComponent,
    data: {
      title: 'List-Client'
    }
  }
];
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ModalModule.forRoot()
  ],
  exports: [RouterModule]
})
export class ListClientModule { }
