import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordhouseComponent } from './recordhouse.component';

describe('RecordhouseComponent', () => {
  let component: RecordhouseComponent;
  let fixture: ComponentFixture<RecordhouseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecordhouseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordhouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
