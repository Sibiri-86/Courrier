export interface IAgent {
    id?: number;
    nom?: string;
    prenom?: string;
    email?: string;
    tel?: string;
    codePays?: string;
    fonction?: string;
    siteId?: number;
}

export class Agent implements IAgent {
    constructor(
        public id?: number,
        public nom?: string,
        public prenom?: string,
        public email?: string,
        public tel?: string,
        public codePays?: string,
        public fonction?: string,
        public siteId?: number
    ) {}
}
