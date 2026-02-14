import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DepartmentListComponent } from './departments/departments.component';
import { AssetListComponent } from './asset/asset.component';
import { DepartmentCreateComponent } from './department-create/department-create.component';
import { AssetCreateComponent } from './asset-create/asset-create.component';
import { AssetActionsComponent } from './asset-actions/asset-actions.component';
import { AssetDetailsComponent } from './asset-details/asset-details.component';
import { DepartmentDetailsComponent } from './department-details/department-details.component'
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    DepartmentListComponent,
    AssetListComponent,
    DepartmentCreateComponent,
    AssetCreateComponent,
    AssetActionsComponent,
    AssetDetailsComponent,
    DepartmentDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
