import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserLoginComponent } from './user-login/user-login.component';
import {HistorylogDetailsComponent } from './historylog-details/historylog-details.component';


const routes: Routes = [
  { path: 'login', component: UserLoginComponent }, 
  { path: 'historylogdetails', component: HistorylogDetailsComponent },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
