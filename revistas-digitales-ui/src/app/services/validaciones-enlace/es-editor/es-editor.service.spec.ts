import { TestBed } from '@angular/core/testing';

import { EsEditorService } from './es-editor.service';

describe('EsEditorService', () => {
  let service: EsEditorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EsEditorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
