import { TestBed } from '@angular/core/testing';

import { EsSuscriptorService } from './es-suscriptor.service';

describe('EsSuscriptorService', () => {
  let service: EsSuscriptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EsSuscriptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
