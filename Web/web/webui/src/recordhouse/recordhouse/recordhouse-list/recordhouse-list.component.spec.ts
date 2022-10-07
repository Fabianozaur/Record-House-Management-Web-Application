import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordhouseListComponent } from './recordhouse-list.component';

describe('RecordhouseListComponent', () => {
  let component: RecordhouseListComponent;
  let fixture: ComponentFixture<RecordhouseListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecordhouseListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordhouseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
