import { Component } from '@angular/core';
import { AssetService } from '../service/asset.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-asset-create',
  template: `
    <h2>Create Asset</h2>
    <form [formGroup]="form" (ngSubmit)="submit()">
      <label>Asset Name:</label>
      <input formControlName="assetName" />
      <label>Category:</label>
      <input formControlName="assetCategory" />
      <label>Purchase Cost:</label>
      <input type="number" formControlName="purchaseCost" />
      <label>Useful Life (years):</label>
      <input type="number" formControlName="assetUsefulLife" />
      <button type="submit" [disabled]="form.invalid">Create</button>
    </form>
    <p *ngIf="successMsg">{{successMsg}}</p>
  `
})
export class AssetCreateComponent {
  form: FormGroup;
  successMsg: string = '';

  constructor(private fb: FormBuilder, private assetService: AssetService) {
    this.form = fb.group({
      assetName: ['', Validators.required],
      assetCategory: ['', Validators.required],
      purchaseCost: [0, Validators.required],
      assetUsefulLife: [1, Validators.required]
    });
  }

  submit() {
    if (this.form.valid) {
      this.assetService.create(this.form.value).subscribe(
        res => {this.successMsg = `Asset ${res.assetName} created!`, alert(this.successMsg);},
        err => {console.error(err), alert(err.error.message)}
      );
      this.form.reset();
    }
  }
}
