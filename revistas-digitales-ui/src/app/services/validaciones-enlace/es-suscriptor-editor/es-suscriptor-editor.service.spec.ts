import { TestBed } from '@angular/core/testing';

import { EsSuscriptorEditorService } from './es-suscriptor-editor.service';

describe('EsSuscriptorEditorService', () => {
  let service: EsSuscriptorEditorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EsSuscriptorEditorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
