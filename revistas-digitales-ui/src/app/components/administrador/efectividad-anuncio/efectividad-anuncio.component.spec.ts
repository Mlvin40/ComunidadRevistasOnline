import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EfectividadAnuncioComponent } from './efectividad-anuncio.component';

describe('EfectividadAnuncioComponent', () => {
  let component: EfectividadAnuncioComponent;
  let fixture: ComponentFixture<EfectividadAnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EfectividadAnuncioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EfectividadAnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
