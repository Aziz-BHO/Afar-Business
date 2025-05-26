import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';

import {RequestEditEntrepriseComponent} from '../request-edit-entreprise/request-edit-entreprise.component';
import {DemandeEntrepriseComponent} from './demande-entreprise.component';


const routes: Routes = [
  {
    path: '',
    component: DemandeEntrepriseComponent,
    data: {
      title: 'Demandes-Entreprise'
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
export class DemandeEntrepriseModule { }
