import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssetActionsComponent } from './asset-actions.component';

describe('AssetActionsComponent', () => {
  let component: AssetActionsComponent;
  let fixture: ComponentFixture<AssetActionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssetActionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssetActionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
