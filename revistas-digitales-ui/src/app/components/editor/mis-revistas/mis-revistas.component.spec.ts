import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MisRevistasComponent } from './mis-revistas.component';

describe('MisRevistasComponent', () => {
  let component: MisRevistasComponent;
  let fixture: ComponentFixture<MisRevistasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MisRevistasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MisRevistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
