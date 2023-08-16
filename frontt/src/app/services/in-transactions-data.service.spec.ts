import { TestBed } from '@angular/core/testing';

import { DataService } from './in-transactions-data.service';

describe('InTransactionsDataService', () => {
  let service: DataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
