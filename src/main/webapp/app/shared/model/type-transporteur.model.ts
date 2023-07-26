import { IFournisseur } from 'app/shared/model/fournisseur.model';

export interface ITypeTransporteur {
    id?: number;
    code?: string;
    libelle?: string;
    fournisseurs?: IFournisseur[];
}

export class TypeTransporteur implements ITypeTransporteur {
    constructor(public id?: number, public code?: string, public libelle?: string, public fournisseurs?: IFournisseur[]) {}
}
