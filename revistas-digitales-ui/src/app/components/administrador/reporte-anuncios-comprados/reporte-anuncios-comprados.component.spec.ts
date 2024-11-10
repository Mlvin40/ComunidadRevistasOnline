import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteAnunciosCompradosComponent } from './reporte-anuncios-comprados.component';

describe('ReporteAnunciosCompradosComponent', () => {
  let component: ReporteAnunciosCompradosComponent;
  let fixture: ComponentFixture<ReporteAnunciosCompradosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteAnunciosCompradosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteAnunciosCompradosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
