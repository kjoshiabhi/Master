import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorylogDetailsComponent } from './historylog-details.component';

describe('HistorylogDetailsComponent', () => {
  let component: HistorylogDetailsComponent;
  let fixture: ComponentFixture<HistorylogDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistorylogDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistorylogDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
