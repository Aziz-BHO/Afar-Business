import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestEditEntrepriseComponent } from './request-edit-entreprise.component';

describe('RequestEditEntrepriseComponent', () => {
  let component: RequestEditEntrepriseComponent;
  let fixture: ComponentFixture<RequestEditEntrepriseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestEditEntrepriseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestEditEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
