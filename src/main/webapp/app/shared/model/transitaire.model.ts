export interface ITransitaire {
    id?: number;
    nom?: string;
    email?: string;
    tel1?: string;
    tel2?: string;
    codePays1?: string;
    codePays2?: string;
    paysId?: number;
}

export class Transitaire implements ITransitaire {
    constructor(
        public id?: number,
        public nom?: string,
        public email?: string,
        public tel1?: string,
        public tel2?: string,
        public codePays1?: string,
        public codePays2?: string,
        public paysId?: number
    ) {}
}
