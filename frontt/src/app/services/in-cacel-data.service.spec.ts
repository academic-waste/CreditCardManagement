import { TestBed } from '@angular/core/testing';

import { InCacelDataService } from './in-cacel-data.service';

describe('InCacelDataService', () => {
  let service: InCacelDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InCacelDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
