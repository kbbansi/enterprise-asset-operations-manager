import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AssetService, Asset } from '../service/asset.service';
import { DepartmentService, Department } from '../service/department.service';

@Component({
  selector: 'app-asset-actions',
  template: `
    <h2>Assign Asset</h2>
    <form [formGroup]="assignForm" (ngSubmit)="assign()">
      <label>Select Asset:</label>
      <select formControlName="assetUid">
        <option value="" disabled>Select Asset</option>
        <option *ngFor="let a of assets" [value]="a.assetUid">{{a.assetName}} ({{a.assetStatus}})</option>
      </select>

      <label>Select Department:</label>
      <select formControlName="departmentUid">
        <option value="" disabled>Select Department</option>
        <option *ngFor="let d of departments" [value]="d.departmentUid">{{d.departmentName}}</option>
      </select>

      <button type="submit" [disabled]="assignForm.invalid">Assign</button>
    </form>

    <h2>Dispose Asset</h2>
    <form [formGroup]="disposeForm" (ngSubmit)="dispose()">
      <label>Select Asset:</label>
      <select formControlName="assetUid">
        <option value="" disabled>Select Asset</option>
        <option *ngFor="let a of assets" [value]="a.assetUid" [disabled]="a.assetStatus !== 'ASSIGNED'">
          {{a.assetName}} ({{a.assetStatus}})
        </option>
      </select>

      <button type="submit" [disabled]="disposeForm.invalid">Dispose</button>
    </form>

    <p *ngIf="successMsg">{{successMsg}}</p>
  `
})
export class AssetActionsComponent implements OnInit {
  assignForm: FormGroup;
  disposeForm: FormGroup;
  assets: Asset[] = [];
  departments: Department[] = [];
  successMsg: string = '';

  constructor(
    private fb: FormBuilder,
    private assetService: AssetService,
    private deptService: DepartmentService
  ) {
    this.assignForm = this.fb.group({
      assetUid: ['', Validators.required],
      departmentUid: ['', Validators.required]
    });

    this.disposeForm = this.fb.group({
      assetUid: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.assetService.getAll().subscribe(list => this.assets = list);
    this.deptService.getAll().subscribe(list => this.departments = list);
  }

  assign() {
    const { assetUid, departmentUid } = this.assignForm.value;
    this.assetService.assign(assetUid, departmentUid).subscribe({
      next: res => {
        this.successMsg = `Asset ${res.assetName} assigned to department!`;
        alert(this.successMsg);
        this.refreshAssets();
      },
      error: err => { 
        alert(err.error.message);
        console.error(err); 
        this.successMsg = ''; 
      }
    });
    this.assignForm.reset();
  }

  dispose() {
    const { assetUid } = this.disposeForm.value;
    this.assetService.dispose(assetUid).subscribe({
      next: res => {
        this.successMsg = `Asset ${res.assetName} disposed!`;
        alert(this.successMsg);
        this.refreshAssets();
      },
      error: err => { 
        alert(err.error.message);
        console.error(err); 
        this.successMsg = ''; 
      }
    });
    this.disposeForm.reset();
  }

  private refreshAssets() {
    this.assetService.getAll().subscribe(list => this.assets = list);
  }
}
