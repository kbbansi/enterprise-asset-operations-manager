import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '../../environments/environment';
import { ApiResponse } from '../models/api-response.model';

export interface Asset {
  assetUid: string;
  assetName: string;
  assetStatus: string;
  category: string;
  purchaseCost: number;
  assetUsefulLife: number;
  department?: { departmentUid: string; departmentName: string };
}

@Injectable({ providedIn: 'root' })
export class AssetService {
  private api = `${environment.apiUrl}/asset`;

  constructor(private http: HttpClient) {}

  create(asset: any): Observable<Asset> {
    return this.http.post<ApiResponse<Asset>>(this.api, asset)
      .pipe(map(res => res.data!));
  }

  assign(assetUid: string, departmentUid: string): Observable<Asset> {
    return this.http.put<ApiResponse<Asset>>(`${this.api}/${assetUid}/assign`, { departmentUid })
      .pipe(map(res => res.data!));
  }

  dispose(assetUid: string): Observable<Asset> {
    return this.http.put<ApiResponse<Asset>>(`${this.api}/${assetUid}/dispose`, {})
      .pipe(map(res => res.data!));
  }

  getAll(): Observable<Asset[]> {
    return this.http.get<ApiResponse<Asset>>(this.api)
      .pipe(map(res => res.dataList || []));
  }

  getByUid(assetUid: string): Observable<Asset> {
    return this.http.get<ApiResponse<Asset>>(`${this.api}/${assetUid}`)
      .pipe(map(res => res.data!));
  }

  getDetails(assetUid: string): Observable<Asset> {
    return this.http.get<ApiResponse<Asset>>(`${this.api}/${assetUid}/details`)
      .pipe(map(res => res.data!));
  }
}
