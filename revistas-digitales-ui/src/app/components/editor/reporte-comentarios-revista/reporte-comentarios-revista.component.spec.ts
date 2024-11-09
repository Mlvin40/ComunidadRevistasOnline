import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteComentariosRevistaComponent } from './reporte-comentarios-revista.component';

describe('ReporteComentariosRevistaComponent', () => {
  let component: ReporteComentariosRevistaComponent;
  let fixture: ComponentFixture<ReporteComentariosRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteComentariosRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteComentariosRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
