import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DepartmentListComponent } from './departments/departments.component';
import { AssetListComponent } from './asset/asset.component';
import { DepartmentCreateComponent } from './department-create/department-create.component';
import { AssetCreateComponent } from './asset-create/asset-create.component';
import { AssetActionsComponent } from './asset-actions/asset-actions.component';
import { DepartmentDetailsComponent } from './department-details/department-details.component';
import { AssetDetailsComponent } from './asset-details/asset-details.component';

const routes: Routes = [
  { path: 'departments', component: DepartmentListComponent },
  { path: 'departments/create', component: DepartmentCreateComponent },
  { path: 'departments/details', component: DepartmentDetailsComponent },
  { path: 'assets', component: AssetListComponent },
  { path: 'assets/create', component: AssetCreateComponent },
  { path: 'assets/actions', component: AssetActionsComponent },
  { path: 'assets/details', component: AssetDetailsComponent },
  { path: '', redirectTo: 'assets', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
