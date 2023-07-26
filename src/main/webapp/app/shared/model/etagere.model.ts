export interface IEtagere {
    id?: number;
    numero?: number;
}

export class Etagere implements IEtagere {
    constructor(public id?: number, public numero?: number) {}
}
