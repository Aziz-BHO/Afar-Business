import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';

import {WidgetsComponent} from '../widgets/widgets.component';
import {AddCategoryComponent} from './add-category.component';


const routes: Routes = [
  {
    path: '',
    component: AddCategoryComponent,
    data: {
      title: 'Add-Category'
    }
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddCategoryModule { }
