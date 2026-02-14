import { Component } from '@angular/core';
import { DepartmentService, DepartmentWithAssets } from '../service/department.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-department-details',
  template: `
    <h2>Department Details</h2>
    <form [formGroup]="form" (ngSubmit)="getDetails()">
      <label>Department UID:</label>
      <input formControlName="departmentUid" />
      <button type="submit" [disabled]="form.invalid">Get Details</button>
    </form>

    <div *ngIf="department">
      <p>Name: {{department.departmentName}}</p>
      <p>UID: {{department.departmentUid}}</p>
      <h3>Assets:</h3>
      <ul>
        <li *ngFor="let a of department.assets">
          {{a.assetName}} ({{a.assetUid}}) - Status: {{a.assetStatus}}
        </li>
      </ul>
    </div>
  `
})
export class DepartmentDetailsComponent {
  form: FormGroup;
  department: DepartmentWithAssets | null = null;

  constructor(private fb: FormBuilder, private deptService: DepartmentService) {
    this.form = fb.group({
      departmentUid: ['', Validators.required]
    });
  }

  getDetails() {
    const { departmentUid } = this.form.value;
    this.deptService.getDetails(departmentUid).subscribe(
      res => this.department = res,
      err => console.error(err)
    );
  }
}
