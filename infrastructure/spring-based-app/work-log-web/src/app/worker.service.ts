import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Worker} from './model/worker';
import {EditWorker} from './model/edit-worker';
import 'rxjs/add/operator/map';

@Injectable()
export class WorkerService {

    constructor(private http: Http) {}

    loginWorker(login: string) {
        return this.http.get('http://localhost:8080/worker/'.concat(login))
            .map((res: Response) => res.json());
    }
    
    createWorker(worker: Worker) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        
        return this.http
            .put('http://localhost:8080/worker/', JSON.stringify(worker), {headers: headers})
            .map((res: Response) => res.json());
    }
    
    deleteWorker(login: string) {
        return this.http.delete('http://localhost:8080/worker/'.concat(login))
            .map((res: Response) => res.json());
    }
    
    editWorker(login: string, editWorker: EditWorker) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        
        return this.http
            .post('http://localhost:8080/worker/'.concat(login), JSON.stringify(editWorker), {headers: headers})
            .map((res: Response) => res.json());
    }
    
}
