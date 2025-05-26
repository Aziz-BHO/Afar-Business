import {Component, OnInit, ViewChild} from '@angular/core';
import {EntrepriseServiceService} from '../../entreprise-service.service';
import {Router} from '@angular/router';
import {BsModalService, ModalDirective} from 'ngx-bootstrap/modal';
import {ToastrService} from 'ngx-toastr';
import {Entreprise} from '../../entreprise.model';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-demande-entreprise',
  templateUrl: './demande-entreprise.component.html',
  styleUrls: ['./demande-entreprise.component.scss']
})
export class DemandeEntrepriseComponent implements OnInit {
  @ViewChild('myModal') public myModal: ModalDirective;
  demandes: any[] ;
  entreprises: Entreprise[];
  selectedRequest: any ;
  categoryForm: FormGroup ;

  constructor(private entrepriseService: EntrepriseServiceService,
              private router: Router ,
              private modalService: BsModalService,
              private toastr: ToastrService,
              ) { }
  get name() { return this.categoryForm.get('name'); }
  ngOnInit(): void {
    this.entrepriseService.getAllRequests().subscribe(data => {
      this.demandes = data ;
    });
    console.log(this.demandes) ;
  }
  getSelectedData(selectedData: Entreprise): void {

    console.log(selectedData);
    this.selectedRequest = selectedData ;
  }

  validate(id: String) {
    this.entrepriseService.demandeEntrepriseValidate(id).subscribe(res=>{
      this.router.navigate(['/Demandes-Entreprise'],{skipLocationChange:true}).then(()=>{
        this.ngOnInit();
        this.myModal.hide() ;
      }) ;
    })
  }

  cancel(id: String) {
    this.entrepriseService.demandeEntrepriseCancel(id).subscribe(res=>{
      this.router.navigate(['/Demandes-Entreprise'],{skipLocationChange:true}).then(()=>{
        this.ngOnInit();
        this.myModal.hide() ;
      }) ;
    })
  }
}
