export interface ITarif {
    id?: number;
    montant?: number;
    tailleBalleId?: number;
}

export class Tarif implements ITarif {
    constructor(public id?: number, public montant?: number, public tailleBalleId?: number) {}
}
