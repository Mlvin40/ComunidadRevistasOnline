import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncianteHomeComponent } from './anunciante-home.component';

describe('AnuncianteHomeComponent', () => {
  let component: AnuncianteHomeComponent;
  let fixture: ComponentFixture<AnuncianteHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncianteHomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncianteHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
