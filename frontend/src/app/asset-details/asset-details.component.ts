import { Component } from '@angular/core';
import { AssetService } from '../service/asset.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Asset } from '../service/asset.service';

@Component({
  selector: 'app-asset-details',
  template: `
    <h2>Asset Details</h2>
    <form [formGroup]="form" (ngSubmit)="getDetails()">
      <label>Asset UID:</label>
      <input formControlName="assetUid" />
      <button type="submit" [disabled]="form.invalid">Get Details</button>
    </form>

    <div *ngIf="asset">
      <p>Name: {{asset.assetName}}</p>
      <p>UID: {{asset.assetUid}}</p>
      <p>Status: {{asset.assetStatus}}</p>
      <p>Category: {{asset.category}}</p>
      <p>Department: {{asset.department?.departmentName || 'Unassigned'}}</p>
      <p>Purchase Cost: {{asset.purchaseCost}}</p>
      <p>Useful Life: {{asset.assetUsefulLife}}</p>
    </div>
  `
})
export class AssetDetailsComponent {
  form: FormGroup;
  asset: Asset | null = null;

  constructor(private fb: FormBuilder, private assetService: AssetService) {
    this.form = fb.group({
      assetUid: ['', Validators.required]
    });
  }

  getDetails() {
    const { assetUid } = this.form.value;
    this.assetService.getDetails(assetUid).subscribe(
      res => this.asset = res,
      err => console.error(err)
    );
  }
}
