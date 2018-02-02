import { Component, Input, Output, EventEmitter } from '@angular/core';
import {Router} from '@angular/router';
import {WorkLog} from '../model/work-log';
import {EditWorkLog} from '../model/edit-work-log';
import {WorkLogService} from '../work-log.service';

@Component({
  selector: 'app-work-log',
  templateUrl: './work-log.component.html',
  styleUrls: ['./work-log.component.css'],
  providers: [WorkLogService]
})
export class WorkLogComponent {

    @Input() workLog: WorkLog;
    editing: boolean = false;
    @Output() deleted:EventEmitter<number> = new EventEmitter();
    
    constructor(private workLogService: WorkLogService, private router: Router) {}
    
    convertSecondsToTime(seconds: number){
        var date = new Date(null);
        date.setSeconds(seconds); // specify value for SECONDS here
        return date.toISOString().substr(11, 8);
    }
    
    enableEditing(){
        this.editing = true;
    }
    
    logWork(){
        let editWorkLog: EditWorkLog = new EditWorkLog(this.workLog.startDate.toString(), this.workLog.timeSpentInSeconds, this.workLog.description);
        this.workLogService.editWorkLog(this.workLog.workLogId, editWorkLog).subscribe(workLog => this.workLog = workLog, error => error, () => this.onLogWorkComplete());
    }

    onLogWorkComplete(){
        this.editing = false;
    }
    
    deleteWorkLog(){
        this.workLogService.deleteWorkLog(this.workLog.workLogId).subscribe(workLog => workLog, error => error, () => this.onDeleteLogWorkComplete());
    }
    
    onDeleteLogWorkComplete(){
        this.deleted.emit(this.workLog.workLogId);
    }
}
