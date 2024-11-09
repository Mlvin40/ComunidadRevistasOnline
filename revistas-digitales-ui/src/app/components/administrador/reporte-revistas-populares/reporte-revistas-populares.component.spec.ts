import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteRevistasPopularesComponent } from './reporte-revistas-populares.component';

describe('ReporteRevistasPopularesComponent', () => {
  let component: ReporteRevistasPopularesComponent;
  let fixture: ComponentFixture<ReporteRevistasPopularesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteRevistasPopularesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteRevistasPopularesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
