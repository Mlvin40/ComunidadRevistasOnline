import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarRevistaComponent } from './editar-revista.component';

describe('EditarRevistaComponent', () => {
  let component: EditarRevistaComponent;
  let fixture: ComponentFixture<EditarRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
