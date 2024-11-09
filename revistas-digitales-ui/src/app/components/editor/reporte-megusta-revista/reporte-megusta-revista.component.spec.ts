import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteMegustaRevistaComponent } from './reporte-megusta-revista.component';

describe('ReporteMegustaRevistaComponent', () => {
  let component: ReporteMegustaRevistaComponent;
  let fixture: ComponentFixture<ReporteMegustaRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteMegustaRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteMegustaRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
