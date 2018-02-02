import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewWorkLogComponent } from './new-work-log.component';

describe('NewWorkLogComponent', () => {
  let component: NewWorkLogComponent;
  let fixture: ComponentFixture<NewWorkLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewWorkLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewWorkLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
