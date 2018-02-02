
export class NewWorkLog {

    startDate: string;
    timeSpentInSeconds: number;
    associatedWorkerLogin: string;
    description: string;
    constructor(associatedWorkerLogin: string, startDate?: string, timeSpentInSeconds?: number, description?: string){
        this.startDate = startDate;
        this.timeSpentInSeconds = timeSpentInSeconds;
        this.associatedWorkerLogin = associatedWorkerLogin;
        this.description = description;
        
    }
}

