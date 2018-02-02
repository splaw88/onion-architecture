import {Worker} from './worker';

export class WorkLog {

    workLogId: number;
    startDate: Date;
    timeSpentInSeconds: number;
    associatedWorker: Worker;
    description: string;
    constructor(workLogId: number, startDate: Date, timeSpentInSeconds: number, associatedWorker: Worker, description: string) {
        this.workLogId = workLogId;
        this.startDate = startDate;
        this.timeSpentInSeconds = timeSpentInSeconds;
        this.associatedWorker = associatedWorker;
        this.description = description;
    }


}