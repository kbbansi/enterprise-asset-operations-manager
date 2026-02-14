import { Component } from '@angular/core';
import { DepartmentService, Department } from '../service/department.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-department-create',
  template: `
    <h2>Create Department</h2>
    <form [formGroup]="form" (ngSubmit)="submit()">
      <label>Department Name:</label>
      <input formControlName="departmentName" />
      <button type="submit" [disabled]="form.invalid">Create</button>
    </form>
    <p *ngIf="successMsg">{{successMsg}}</p>
  `
})
export class DepartmentCreateComponent {
  form: FormGroup;
  successMsg: string = '';

  constructor(private fb: FormBuilder, private deptService: DepartmentService) {
    this.form = fb.group({
      departmentName: ['', Validators.required]
    });
  }

  submit() {
    if (this.form.valid) {
      this.deptService.create(this.form.value).subscribe(
        res => {this.successMsg = `Department ${res.departmentName} created!`, alert(this.successMsg);},
        err => {console.error(err), alert(err.error.message)}
      );
      this.form.reset();
    }
  }
}
