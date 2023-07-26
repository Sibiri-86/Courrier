import { ITarif } from 'app/shared/model/tarif.model';

export interface ITailleBalle {
    id?: number;
    code?: string;
    libelle?: string;
    volume?: number;
    tarifs?: ITarif[];
}

export class TailleBalle implements ITailleBalle {
    constructor(public id?: number, public code?: string, public libelle?: string, public volume?: number, public tarifs?: ITarif[]) {}
}
