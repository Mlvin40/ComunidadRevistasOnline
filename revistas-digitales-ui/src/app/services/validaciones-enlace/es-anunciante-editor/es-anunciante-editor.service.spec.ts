import { TestBed } from '@angular/core/testing';

import { EsAnuncianteEditorService } from './es-anunciante-editor.service';

describe('EsAnuncianteEditorService', () => {
  let service: EsAnuncianteEditorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EsAnuncianteEditorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
