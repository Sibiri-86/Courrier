export interface IClient {
    id?: number;
    nom?: string;
    prenom?: string;
    email?: string;
    tel?: string;
    codePays?: string;
    codePays1?: string;
    whatsap?: string;
    longitude?: number;
    latitude?: number;
    paysId?: number;
}

export class Client implements IClient {
    constructor(
        public id?: number,
        public nom?: string,
        public prenom?: string,
        public email?: string,
        public tel?: string,
        public codePays?: string,
        public codePays1?: string,
        public whatsap?: string,
        public longitude?: number,
        public latitude?: number,
        public paysId?: number
    ) {}
}
