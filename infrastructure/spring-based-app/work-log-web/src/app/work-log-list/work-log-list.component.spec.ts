import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkLogListComponent } from './work-log-list.component';

describe('WorkLogListComponent', () => {
  let component: WorkLogListComponent;
  let fixture: ComponentFixture<WorkLogListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkLogListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkLogListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
