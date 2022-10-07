import { TestBed } from '@angular/core/testing';

import { RecordhouseService } from './recordhouse.service';

describe('RecordhouseService', () => {
  let service: RecordhouseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecordhouseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
