import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelCardComponent } from './cancel-card.component';

describe('CancelCardComponent', () => {
  let component: CancelCardComponent;
  let fixture: ComponentFixture<CancelCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CancelCardComponent]
    });
    fixture = TestBed.createComponent(CancelCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
