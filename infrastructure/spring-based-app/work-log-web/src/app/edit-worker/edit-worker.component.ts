import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {EditWorker} from '../model/edit-worker';
import {Worker} from '../model/worker';
import {WorkerService} from '../worker.service';

@Component({
  selector: 'app-edit-worker',
  templateUrl: './edit-worker.component.html',
  styleUrls: ['./edit-worker.component.css'],
  providers: [WorkerService]
})
export class EditWorkerComponent {


    editedWorker: EditWorker = new Worker();
    login: string;
    newWorker: Worker;

    constructor(private workerService: WorkerService, private router: Router) {
        let worker: Worker = JSON.parse(localStorage.getItem('worker'));
        this.login = worker.login;
        this.editedWorker.name = worker.name;
        this.editedWorker.surname = worker.surname;
        this.editedWorker.email = worker.email;
    }

    editWorker() {
        this.workerService.editWorker(this.login, this.editedWorker).subscribe(worker => this.newWorker = worker, error => error, () => this.onComplete());
    }
    
    onComplete(){
        localStorage.setItem('worker', JSON.stringify(this.newWorker));
        this.router.navigate(['/work-log-page']);
    }

}
