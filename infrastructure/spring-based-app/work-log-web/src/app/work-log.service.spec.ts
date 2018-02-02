import { TestBed, inject } from '@angular/core/testing';

import { WorkLogService } from './work-log.service';

describe('WorkLogService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WorkLogService]
    });
  });

  it('should be created', inject([WorkLogService], (service: WorkLogService) => {
    expect(service).toBeTruthy();
  }));
});
