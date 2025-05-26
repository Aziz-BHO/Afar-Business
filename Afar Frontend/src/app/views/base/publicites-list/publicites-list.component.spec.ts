import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicitesListComponent } from './publicites-list.component';

describe('PublicitesListComponent', () => {
  let component: PublicitesListComponent;
  let fixture: ComponentFixture<PublicitesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicitesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicitesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
