import { IAgent } from 'app/shared/model/agent.model';

export interface ISite {
    id?: number;
    libelle?: string;
    agents?: IAgent[];
    paysId?: number;
}

export class Site implements ISite {
    constructor(public id?: number, public libelle?: string, public agents?: IAgent[], public paysId?: number) {}
}
