import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginWorkerComponent } from './login-worker.component';

describe('LoginWorkerComponent', () => {
  let component: LoginWorkerComponent;
  let fixture: ComponentFixture<LoginWorkerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginWorkerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginWorkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
