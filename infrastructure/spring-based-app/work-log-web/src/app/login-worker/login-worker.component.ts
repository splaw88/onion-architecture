import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {WorkerService} from '../worker.service';
import {Worker} from '../model/worker';

@Component({
    selector: 'app-login-worker',
    templateUrl: './login-worker.component.html',
    styleUrls: ['./login-worker.component.css'],
    providers: [WorkerService]
})
export class LoginWorkerComponent {

    constructor(private workerService: WorkerService, private router: Router) {}
    login = "";
    worker: Worker;
    
    loginWorker() {
        this.workerService.loginWorker(this.login).subscribe(worker => this.worker = worker, error => error, () => this.onComplete());
        
    }
    
    onComplete(){
        localStorage.setItem('worker', JSON.stringify(this.worker));
        this.router.navigate(['/work-log-page']);
    }
    
    goNewWorker(){
        this.router.navigate(['/create-worker-page']);
    }

}
