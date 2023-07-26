export interface IRayon {
    id?: number;
    numero?: number;
}

export class Rayon implements IRayon {
    constructor(public id?: number, public numero?: number) {}
}
