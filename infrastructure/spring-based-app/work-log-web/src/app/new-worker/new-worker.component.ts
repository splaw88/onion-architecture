import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {Worker} from '../model/worker';
import {WorkerService} from '../worker.service';

@Component({
  selector: 'app-new-worker',
  templateUrl: './new-worker.component.html',
  styleUrls: ['./new-worker.component.css'],
  providers: [WorkerService]
})
export class NewWorkerComponent {

constructor(private workerService: WorkerService, private router: Router) {}

    newWorker: Worker = new Worker();
    
    createWorker(){
        this.workerService.createWorker(this.newWorker).subscribe(worker => this.newWorker = worker, error => error, () => this.onComplete());
    }
    
    onComplete(){
        localStorage.setItem('worker', JSON.stringify(this.newWorker));
        this.router.navigate(['/work-log-page']);
    }
}
