import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MisSuscripcionesComponent } from './mis-suscripciones.component';

describe('MisSuscripcionesComponent', () => {
  let component: MisSuscripcionesComponent;
  let fixture: ComponentFixture<MisSuscripcionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MisSuscripcionesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MisSuscripcionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
