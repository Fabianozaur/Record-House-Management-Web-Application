import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordhouseNewComponent } from './recordhouse-new.component';

describe('RecordhouseNewComponent', () => {
  let component: RecordhouseNewComponent;
  let fixture: ComponentFixture<RecordhouseNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecordhouseNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordhouseNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
