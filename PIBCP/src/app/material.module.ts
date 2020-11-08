import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { MatTableModule } from '@angular/material/table';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MaterialFileInputModule } from 'ngx-material-file-input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { RatingModule } from 'ng-starrating';
import { ChartsModule } from 'ng2-charts';
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatTabsModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatCardModule,
    MatButtonModule,
    MatChipsModule,
    MatTableModule,
    MatProgressBarModule,
    MaterialFileInputModule,
    MatTableModule,
    MatPaginatorModule,
    RatingModule,
    ChartsModule
  ],
  exports: [
    MatFormFieldModule,
    MatTabsModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatCardModule,
    MatButtonModule,
    MatChipsModule,
    MatTableModule,
    MatProgressBarModule,
    MaterialFileInputModule,
    MatTableModule,
    MatPaginatorModule,
    RatingModule,
    ChartsModule
  ]
})
export class MaterialModule { }
