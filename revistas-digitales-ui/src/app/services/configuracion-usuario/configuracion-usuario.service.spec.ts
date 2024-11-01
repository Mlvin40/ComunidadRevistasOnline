import { TestBed } from '@angular/core/testing';

import { ConfiguracionUsuarioService } from './configuracion-usuario.service';

describe('ConfiguracionUsuarioService', () => {
  let service: ConfiguracionUsuarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConfiguracionUsuarioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
