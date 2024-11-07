import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CambiarCostoRevistaComponent } from './cambiar-costo-revista.component';

describe('CambiarCostoRevistaComponent', () => {
  let component: CambiarCostoRevistaComponent;
  let fixture: ComponentFixture<CambiarCostoRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CambiarCostoRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CambiarCostoRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
