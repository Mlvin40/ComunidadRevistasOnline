import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncioIndividualComponent } from './anuncio-individual.component';

describe('AnuncioIndividualComponent', () => {
  let component: AnuncioIndividualComponent;
  let fixture: ComponentFixture<AnuncioIndividualComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncioIndividualComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncioIndividualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
