import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {WorkLogService} from '../work-log.service';
import {WorkerService} from '../worker.service';
import {WorkLog} from '../model/work-log';
import {Worker} from '../model/worker';

@Component({
    selector: 'app-work-log-list',
    templateUrl: './work-log-list.component.html',
    styleUrls: ['./work-log-list.component.css'],
    providers: [WorkLogService, WorkerService]
})
export class WorkLogListComponent {

    workLogList: Array<WorkLog>;
    worker: Worker;

    constructor(private workLogService: WorkLogService, private workerService: WorkerService, private router: Router) {
        this.worker = JSON.parse(localStorage.getItem('worker'));
        this.getWorkLogList();
    }
    
    deleteWorker(){
        this.workerService.deleteWorker(this.worker.login).subscribe(worker => this.worker = worker, error => error, () => this.onWorkerDeleteSuccess());
    }
    
    
    goEditWorker(){
        this.router.navigate(['edit-worker-page']);
    }

    goLogWork(){
        this.router.navigate(['new-work-log-page']);
    }

    onWorkerDeleteSuccess(){
        localStorage.removeItem('worker');
        this.router.navigate(['']);
    }
    
    getWorkLogList(){
        this.workLogService.listWorkLogs(this.worker.login).subscribe(workLogList => this.workLogList = workLogList); 
    }
    
    onDeleteEvent(event){
        this.getWorkLogList();
    }

}
