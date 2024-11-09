import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteSuscripcionRevistaComponent } from './reporte-suscripcion-revista.component';

describe('ReporteSuscripcionRevistaComponent', () => {
  let component: ReporteSuscripcionRevistaComponent;
  let fixture: ComponentFixture<ReporteSuscripcionRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteSuscripcionRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteSuscripcionRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
