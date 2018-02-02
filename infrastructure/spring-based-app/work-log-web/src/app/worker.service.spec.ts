import { TestBed, inject } from '@angular/core/testing';

import { WorkerService } from './worker.service';

describe('WorkerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WorkerService]
    });
  });

  it('should be created', inject([WorkerService], (service: WorkerService) => {
    expect(service).toBeTruthy();
  }));
});
