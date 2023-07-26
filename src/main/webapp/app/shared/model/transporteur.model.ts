export interface ITransporteur {
    id?: number;
    libelle?: string;
}

export class Transporteur implements ITransporteur {
    constructor(public id?: number, public libelle?: string) {}
}
