import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CostoGlobalRevistaComponent } from './costo-global-revista.component';

describe('CostoGlobalRevistaComponent', () => {
  let component: CostoGlobalRevistaComponent;
  let fixture: ComponentFixture<CostoGlobalRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CostoGlobalRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CostoGlobalRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
