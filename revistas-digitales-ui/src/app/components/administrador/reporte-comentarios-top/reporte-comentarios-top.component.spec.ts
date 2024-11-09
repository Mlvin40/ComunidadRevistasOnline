import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteComentariosTopComponent } from './reporte-comentarios-top.component';

describe('ReporteComentariosTopComponent', () => {
  let component: ReporteComentariosTopComponent;
  let fixture: ComponentFixture<ReporteComentariosTopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteComentariosTopComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteComentariosTopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
