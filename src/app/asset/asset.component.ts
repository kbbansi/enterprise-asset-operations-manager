import { Component, OnInit } from '@angular/core';
import { AssetService, Asset } from '../service/asset.service';

@Component({
  selector: 'app-asset-list',
  template: `
    <h2>Assets</h2>
    <ul>
      <li *ngFor="let a of assets">
        {{a.assetName}} ({{a.assetUid}}) - Status: {{a.assetStatus}}
        <span *ngIf="a.department"> - Department: {{a.department.departmentName}}</span>
      </li>
    </ul>
  `
})
export class AssetListComponent implements OnInit {
  assets: Asset[] = [];

  constructor(private assetService: AssetService) {}

  ngOnInit() {
    this.assetService.getAll().subscribe(list => this.assets = list);
  }
}