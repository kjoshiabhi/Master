import { Component, OnInit } from '@angular/core';
import { HttpclientService } from '../service/httpclient.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-historylog-details',
  templateUrl: './historylog-details.component.html',
  styleUrls: ['./historylog-details.component.css']
})
export class HistorylogDetailsComponent implements OnInit {
  historylog = [];
  data = [];
  obj=[];
  
  constructor(private userService: HttpclientService) { }

  ngOnInit() {
    
  }

  onSubmit() {
    this.loadAllUsers();
  }

  loadAllUsers() {
    this.userService.getLogs()
        .pipe(first())
        .subscribe(historylog => {
          this.historylog = this.historylog["obj"] ;
          
          console.log(this.historylog["data"]);
        }
          );
}
}
