import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ListEntrepriseComponent} from '../../list-entreprise/list-entreprise.component';
import {ListCommandsComponent} from './list-commands.component';
import {BsModalRef, ModalModule} from 'ngx-bootstrap/modal';



const routes: Routes = [
  {
    path: '',
    component: ListCommandsComponent,
    data: {
      title: 'List-Commands'
    }
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ModalModule.forRoot() ,

  ],
  exports: [RouterModule],
  providers: [ BsModalRef] ,
})
export class ListCommandsModule { }
