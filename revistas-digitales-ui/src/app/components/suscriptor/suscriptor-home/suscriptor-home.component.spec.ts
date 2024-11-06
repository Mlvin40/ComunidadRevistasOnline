import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuscriptorHomeComponent } from './suscriptor-home.component';

describe('SuscriptorHomeComponent', () => {
  let component: SuscriptorHomeComponent;
  let fixture: ComponentFixture<SuscriptorHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuscriptorHomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuscriptorHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
