import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarSaldoCarteraComponent } from './mostrar-saldo-cartera.component';

describe('MostrarSaldoCarteraComponent', () => {
  let component: MostrarSaldoCarteraComponent;
  let fixture: ComponentFixture<MostrarSaldoCarteraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MostrarSaldoCarteraComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostrarSaldoCarteraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
