import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PublicityService } from '../../publicity.service';

@Component({
  selector: 'app-cv-uploader',
  templateUrl: './cv-uploader.component.html',
  styleUrls: ['./cv-uploader.component.scss']
})
export class CvUploaderComponent implements OnInit {
  publicityId: string;
  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };
  url;
  format;

  constructor(
    private publicityService: PublicityService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { 
    this.activatedRoute.params.subscribe((param) => {
      this.publicityId = param.pId;
    });
  }

  ngOnInit(): void {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.publicityService.uploadFile(this.currentFileUpload, this.publicityId).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        this.router.navigate(['/publicities/list']);
      }
    });

    this.selectedFiles = undefined;
  }

  onSelectFile(event) {
    this.selectedFiles = event.target.files;
    const file = event.target.files && event.target.files[0];
    if (file) {
      var reader = new FileReader();
      reader.readAsDataURL(file);
      if(file.type.indexOf('image')> -1){
        this.format = 'image';
      } else if(file.type.indexOf('video')> -1){
        this.format = 'video';
      }
      reader.onload = (event) => {
        this.url = (<FileReader>event.target).result;
      }
    }
  }
}
