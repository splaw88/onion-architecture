
export class EditWorkLog {

    startDate: string;
    timeSpentInSeconds: number;
    description: string;
    constructor(startDate?: string, timeSpentInSeconds?: number, description?: string){
        this.startDate = startDate;
        this.timeSpentInSeconds = timeSpentInSeconds;
        this.description = description;
        
    }
}