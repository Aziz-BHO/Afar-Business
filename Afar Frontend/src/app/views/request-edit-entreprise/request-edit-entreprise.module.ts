import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {RequestEditEntrepriseComponent} from './request-edit-entreprise.component';

const routes: Routes = [
  {
    path: '',
    component: RequestEditEntrepriseComponent,
    data: {
      title: 'Request-Edit'
    }
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class RequestEditEntrepriseModule { }
