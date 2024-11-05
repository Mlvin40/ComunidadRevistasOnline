import { TestBed } from '@angular/core/testing';

import { AnuncioTemporalService } from './anuncio-temporal.service';

describe('AnuncioTemporalService', () => {
  let service: AnuncioTemporalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnuncioTemporalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
