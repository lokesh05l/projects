import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserAnswerComponent } from './update-user-answer.component';

describe('UpdateUserAnswerComponent', () => {
  let component: UpdateUserAnswerComponent;
  let fixture: ComponentFixture<UpdateUserAnswerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateUserAnswerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateUserAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
