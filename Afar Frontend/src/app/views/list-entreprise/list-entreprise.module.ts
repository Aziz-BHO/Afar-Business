import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';

import {ListEntrepriseComponent} from './list-entreprise.component';

const routes: Routes = [
  {
    path: '',
    component: ListEntrepriseComponent,
    data: {
      title: 'List-Entreprise'
    }
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
     RouterModule.forChild(routes),
  ],
  exports: [RouterModule]
})
export class ListEntrepriseModule { }
