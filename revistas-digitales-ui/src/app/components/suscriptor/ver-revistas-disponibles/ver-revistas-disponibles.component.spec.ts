import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerRevistasDisponiblesComponent } from './ver-revistas-disponibles.component';

describe('VerRevistasDisponiblesComponent', () => {
  let component: VerRevistasDisponiblesComponent;
  let fixture: ComponentFixture<VerRevistasDisponiblesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerRevistasDisponiblesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerRevistasDisponiblesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
