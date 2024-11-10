import { TestBed } from '@angular/core/testing';

import { EsAdministradorService } from './es-administrador.service';

describe('EsAdministradorService', () => {
  let service: EsAdministradorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EsAdministradorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
