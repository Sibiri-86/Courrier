export interface IFournisseur {
    id?: number;
    nom?: string;
    email?: string;
    contact?: string;
    codePays?: string;
    paysId?: number;
    typeId?: number;
}

export class Fournisseur implements IFournisseur {
    constructor(
        public id?: number,
        public nom?: string,
        public email?: string,
        public contact?: string,
        public codePays?: string,
        public paysId?: number,
        public typeId?: number
    ) {}
}
