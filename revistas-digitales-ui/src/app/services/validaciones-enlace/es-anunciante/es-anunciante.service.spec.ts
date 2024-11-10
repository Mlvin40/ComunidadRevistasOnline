import { TestBed } from '@angular/core/testing';

import { EsAnuncianteService } from './es-anunciante.service';

describe('EsAnuncianteService', () => {
  let service: EsAnuncianteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EsAnuncianteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
