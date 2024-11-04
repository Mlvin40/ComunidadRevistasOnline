import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComprarAnuncioComponent } from './comprar-anuncio.component';

describe('ComprarAnuncioComponent', () => {
  let component: ComprarAnuncioComponent;
  let fixture: ComponentFixture<ComprarAnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComprarAnuncioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComprarAnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
