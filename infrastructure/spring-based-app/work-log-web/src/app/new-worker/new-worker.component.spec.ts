import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewWorkerComponent } from './new-worker.component';

describe('NewWorkerComponent', () => {
  let component: NewWorkerComponent;
  let fixture: ComponentFixture<NewWorkerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewWorkerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewWorkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
