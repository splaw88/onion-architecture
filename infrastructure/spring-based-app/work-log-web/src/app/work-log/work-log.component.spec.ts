import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkLogComponent } from './work-log.component';

describe('WorkLogComponent', () => {
  let component: WorkLogComponent;
  let fixture: ComponentFixture<WorkLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
