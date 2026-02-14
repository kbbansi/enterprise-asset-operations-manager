import { Component, OnInit } from '@angular/core';
import { DepartmentService, Department } from '../service/department.service';

@Component({
  selector: 'app-department-list',
  template: `
    <h2>Departments</h2>
    <ul>
      <li *ngFor="let d of departments">{{d.departmentName}} ({{d.departmentUid}})</li>
    </ul>
  `
})
export class DepartmentListComponent implements OnInit {
  departments: Department[] = [];

  constructor(private deptService: DepartmentService) {}

  ngOnInit() {
    this.deptService.getAll().subscribe(list => this.departments = list);
  }
}