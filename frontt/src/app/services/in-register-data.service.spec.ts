import { TestBed } from '@angular/core/testing';

import { InRegisterDataService } from './in-register-data.service';

describe('InRegisterDataService', () => {
  let service: InRegisterDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InRegisterDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
