import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {NewWorkLog} from '../model/new-work-log';
import {WorkLogService} from '../work-log.service';

@Component({
  selector: 'app-new-work-log',
  templateUrl: './new-work-log.component.html',
  styleUrls: ['./new-work-log.component.css'],
  providers: [WorkLogService]
})
export class NewWorkLogComponent{

    newWorkLog: NewWorkLog;

    constructor(private workLogService: WorkLogService, private router: Router) {
        this.newWorkLog = new NewWorkLog(JSON.parse(localStorage.getItem('worker')).login);
    }

    logWork(){
        this.workLogService.newWorkLog(this.newWorkLog).subscribe(workLog => workLog, error => error, () => this.onComplete());
    }

    onComplete(){
        this.router.navigate(['/work-log-page']);
    }
}
