import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrecioOcultacionAnunciosComponent } from './precio-ocultacion-anuncios.component';

describe('PrecioOcultacionAnunciosComponent', () => {
  let component: PrecioOcultacionAnunciosComponent;
  let fixture: ComponentFixture<PrecioOcultacionAnunciosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrecioOcultacionAnunciosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrecioOcultacionAnunciosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
