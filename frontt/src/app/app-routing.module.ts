import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataDisplayComponent } from './data-display/data-display.component';
import { RegisterCardComponent } from './register-card/register-card.component';
import { CancelCardComponent } from './cancel-card/cancel-card.component';
import { DataAnalysisComponent } from './data-analysis/data-analysis.component';

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: 'datas', component: DataDisplayComponent },
  { path: 'register', component: RegisterCardComponent },
  { path: 'cancel', component: CancelCardComponent },
  { path: 'analysis', component: DataAnalysisComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
  //导出 RouterModule，以便它在整个应用程序中生效
})
export class AppRoutingModule { }