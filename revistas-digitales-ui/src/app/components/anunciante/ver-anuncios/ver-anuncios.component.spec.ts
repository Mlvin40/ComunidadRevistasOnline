import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerAnunciosComponent } from './ver-anuncios.component';

describe('VerAnunciosComponent', () => {
  let component: VerAnunciosComponent;
  let fixture: ComponentFixture<VerAnunciosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerAnunciosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerAnunciosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
