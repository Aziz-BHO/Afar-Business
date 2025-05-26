import {Component, OnInit, ViewChild} from '@angular/core';
import {EntrepriseServiceService} from '../../../entreprise-service.service';
import {Router} from '@angular/router';
import {BsModalRef, BsModalService, ModalDirective} from 'ngx-bootstrap/modal';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../../user.service';
import {Entreprise} from '../../../entreprise.model';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-list-commands',
  templateUrl: './list-commands.component.html',
  styleUrls: ['./list-commands.component.scss']
})
export class ListCommandsComponent implements OnInit {
  @ViewChild('myModal') public myModal: ModalDirective;
  @ViewChild('delModal') public delModal: ModalDirective;
  @ViewChild('desModal') public desModal: ModalDirective;
  commands: any[];
  selectedcommand: any ;
  public isCollapsed = false;
  modalRef: BsModalRef;

  constructor(private entrepriseService: EntrepriseServiceService,
              private router: Router ,
              private modalService: BsModalService,
              private toastr: ToastrService,
              private us: UserService ,
              ) { }


  collapsed(event: any): void {
    // console.log(event);
  }

  expanded(event: any): void {
    // console.log(event);
  }


  ngOnInit(): void {

    this.entrepriseService.getAllCommands().subscribe(data => {
      this.commands = data ;
    });
  }
  getSelectedData(selectedData: any): void {

    console.log(selectedData);
    this.selectedcommand = selectedData ;
  }
  confirmCommand(id: String) {
    this.entrepriseService.validateCommand(id).subscribe(data => {
      this.router.navigate(['/List-Commands'],{skipLocationChange:true}).then(()=>{
        this.ngOnInit();
        this.desModal.hide() ;
      }) ;

    });
  }
  cancelCommand(id: String) {
    this.entrepriseService.cancelcommand(id).subscribe(data => {
      this.router.navigate(['/List-Commands'],{skipLocationChange:true}).then(()=>{
        this.ngOnInit();
        this.myModal.hide() ;
      }) ;
    });
  }
  getColor(country) {
    switch (country) {
      case 'Confirmed':
        return 'green';
      case 'waiting':
        return 'blue';
      case 'Cancled':
        return 'red';
    }
  }

}
