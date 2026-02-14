import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '../../environments/environment';
import { ApiResponse } from '../models/api-response.model';

export interface Department {
  departmentUid: string;
  departmentName: string;
}

export interface DepartmentWithAssets extends Department {
  assets: { assetUid: string; assetName: string; assetStatus: string }[];
}

@Injectable({ providedIn: 'root' })
export class DepartmentService {
  private api = `${environment.apiUrl}/departments`;

  constructor(private http: HttpClient) {}

  create(department: Department): Observable<Department> {
    return this.http.post<ApiResponse<Department>>(this.api, department)
      .pipe(map(res => res.data!));
  }

  getAll(): Observable<Department[]> {
    return this.http.get<ApiResponse<Department>>(this.api)
      .pipe(map(res => res.dataList || []));
  }

  getByUid(uid: string): Observable<Department> {
    return this.http.get<ApiResponse<Department>>(`${this.api}/${uid}`)
      .pipe(map(res => res.data!));
  }

  getDetails(uid: string): Observable<DepartmentWithAssets> {
    return this.http.get<ApiResponse<DepartmentWithAssets>>(`${this.api}/${uid}/details`)
      .pipe(map(res => res.data!));
  }
}
