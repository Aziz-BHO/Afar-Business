import {Component, OnInit, ViewChild} from '@angular/core';

import {Router} from '@angular/router';
import {Client} from '../../client.model';
import {ModalDirective} from 'ngx-bootstrap/modal';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {ClientService} from '../../client.service';
@Component({
  selector: 'app-list-client',
  templateUrl: './list-client.component.html',
  styleUrls: ['./list-client.component.scss']
})
export class ListClientComponent implements OnInit {
  @ViewChild('myModal') public myModal: ModalDirective;
  @ViewChild('delModal') public delModal: ModalDirective;
  clients: Client[] ;
  modalRef: BsModalRef;
  selectedClient: Client ;
  updateClientForm: FormGroup ;
  ID: String ;
  constructor( private clientService: ClientService ,
               private router: Router ,
               private modalService: BsModalService,
               private toastr: ToastrService,
               private fb: FormBuilder,
  ) {
    const formControls = {
      firstName: new FormControl('', [
        Validators.required,
        Validators.pattern('[A-Za-z .\'-]+'),
        Validators.minLength(2)
      ]),
      lastName: new FormControl('', [
        Validators.required,
        Validators.pattern('[A-Za-z .\'-]+'),
        Validators.minLength(2)
      ]),
      phone: new FormControl('', [
        Validators.required,
        Validators.pattern('[0-9]+'),
        Validators.minLength(8),
        Validators.maxLength(13)
      ]),
      email: new FormControl('', [
        Validators.required,
        Validators.email
      ]),
      cin: new FormControl('', [
        Validators.required,
        Validators.minLength(6)
      ]),
    };
    this.updateClientForm = this.fb.group(formControls);
  }
  get name() { return this.updateClientForm.get('name'); }
  ngOnInit(): void {
    this.clientService.getAllclients().subscribe(data => {
      this.clients = data ;
    });
  }


  getSelectedData(selectedData: Client): void {

    console.log(selectedData);
    this.selectedClient = selectedData ;
  }
  update() {

    const data = this.updateClientForm.value;
    const client = new Client(  data.firstName , data.lastName, data.email, this.selectedClient?.password , data.phone, data.cin, this.selectedClient.id );
    this.clientService.updateClient(client).subscribe(res => {
        this.toastr.success(res.message);
        this.router.navigate(['/List-Client'],{skipLocationChange:true}).then(()=>{
          this.ngOnInit();
          this.myModal.hide() ;
        }) ;
      },
      err => {
        console.log(err);
      }
    );
  }
  delete() {
    this.clientService.deleteClient(this.selectedClient.id).subscribe(res => {

        this.router.navigate(['/List-Client'],{skipLocationChange:true}).then(()=>{
          this.ngOnInit();
          this.delModal.hide() ;
        }) ;
      },
      err => {
        console.log(err);
      }
    );
  }
}
