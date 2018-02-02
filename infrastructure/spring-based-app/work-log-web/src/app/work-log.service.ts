import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {NewWorkLog} from './model/new-work-log';
import {EditWorkLog} from './model/edit-work-log';
import 'rxjs/add/operator/map';

@Injectable()
export class WorkLogService {

    constructor(private http: Http) {}

    listWorkLogs(login: string) {
        return this.http.get('http://localhost:8080/work-log/'.concat(login))
            .map((res: Response) => res.json());
    }
    
    newWorkLog(newWorkLog: NewWorkLog){
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        
        return this.http
            .put('http://localhost:8080/work-log/', JSON.stringify(newWorkLog), {headers: headers})
            .map((res: Response) => res.json());
    }
    
    editWorkLog(id: number, workLog: EditWorkLog){
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        
        return this.http
            .post('http://localhost:8080/work-log/'.concat(String(id)), JSON.stringify(workLog), {headers: headers})
            .map((res: Response) => res.json());
    }
    
    deleteWorkLog(id: number) {
        return this.http.delete('http://localhost:8080/work-log/'.concat(String(id)))
            .map((res: Response) => res.json());
    }
}
