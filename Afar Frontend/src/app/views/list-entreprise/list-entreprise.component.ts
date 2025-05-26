import {Component, OnInit, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService, ModalDirective} from 'ngx-bootstrap/modal';
import {Entreprise} from '../../entreprise.model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {EntrepriseServiceService} from '../../entreprise-service.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Category} from '../../category.model';
import {UserService} from '../../user.service';

@Component({
  selector: 'app-list-entreprise',
  templateUrl: './list-entreprise.component.html',
  styleUrls: ['./list-entreprise.component.scss']
})
export class ListEntrepriseComponent implements OnInit {
  @ViewChild('myModal') public myModal: ModalDirective;
  @ViewChild('delModal') public delModal: ModalDirective;
  @ViewChild('desModal') public desModal: ModalDirective;
  entreprises: Entreprise[];
  selectedEntreprise: Entreprise ;
  categoryForm: FormGroup ;
  modalRef: BsModalRef;
  constructor(private entrepriseService: EntrepriseServiceService,
              private router: Router ,
              private modalService: BsModalService,
              private toastr: ToastrService,
              private us: UserService,
              ) {

  }
  get name() { return this.categoryForm.get('name'); }
  ngOnInit(): void {

    this.entrepriseService.getAllUsers().subscribe(data => {
      this.entreprises = data ;
    });
  }
  getSelectedData(selectedData: Entreprise): void {

    console.log(selectedData);
    this.selectedEntreprise = selectedData ;
  }

  activateEntreprise(id: String){
    this.entrepriseService.activateEntreprise(id).subscribe(res => {

        this.router.navigate(['/List-Entreprise'],{skipLocationChange:true}).then(()=>{
          this.ngOnInit();
          this.myModal.hide() ;
        }) ;
      },
      err => {
        console.log(err);
      }
    );
  }

  DesactivateEntreprise(id: String){
    this.entrepriseService.DesactivateEntreprise(id).subscribe(res => {

        this.router.navigate(['/List-Entreprise'],{skipLocationChange:true}).then(()=>{
          this.ngOnInit();
          this.desModal.hide() ;
        }) ;
      },
      err => {
        console.log(err);
      }
    );
  }

  delete() {
    this.entrepriseService.deleteUser(this.selectedEntreprise.id).subscribe(res => {

        this.router.navigate(['/List-Entreprise'],{skipLocationChange:true}).then(()=>{
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
