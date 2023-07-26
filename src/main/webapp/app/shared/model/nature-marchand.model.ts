export interface INatureMarchand {
    id?: number;
    code?: string;
    libelle?: string;
}

export class NatureMarchand implements INatureMarchand {
    constructor(public id?: number, public code?: string, public libelle?: string) {}
}
