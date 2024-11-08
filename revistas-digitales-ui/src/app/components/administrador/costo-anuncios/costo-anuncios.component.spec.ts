import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CostoAnunciosComponent } from './costo-anuncios.component';

describe('CostoAnunciosComponent', () => {
  let component: CostoAnunciosComponent;
  let fixture: ComponentFixture<CostoAnunciosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CostoAnunciosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CostoAnunciosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
