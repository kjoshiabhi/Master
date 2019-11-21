import { TestBed } from '@angular/core/testing';

import { HistorylogDetailsService } from './historylog-details.service';

describe('HistorylogDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HistorylogDetailsService = TestBed.get(HistorylogDetailsService);
    expect(service).toBeTruthy();
  });
});
