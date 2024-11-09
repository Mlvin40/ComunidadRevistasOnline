import { TestBed } from '@angular/core/testing';

import { ReportesAdministradorService } from './reportes-administrador.service';

describe('ReportesAdministradorService', () => {
  let service: ReportesAdministradorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportesAdministradorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
