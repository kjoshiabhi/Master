import { Component, OnInit } from '@angular/core';
import { HistorylogDetailsService } from '../historylog-details.service';
import { HistoryLog } from '../historylog';

import { Router } from '@angular/router';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  
  historylog: HistoryLog = new HistoryLog();
  submitted = false;

  constructor(private historylogService: HistorylogDetailsService,
    private router: Router) { }

  ngOnInit() {
  }

  newHistoryLog(): void {
    this.submitted = false;
    this.historylog = new HistoryLog();
  }

  save() {
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/historylogdetails']);
  }
}
