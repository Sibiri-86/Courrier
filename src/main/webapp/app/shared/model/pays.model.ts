import { ISite } from 'app/shared/model/site.model';
import { ITransitaire } from 'app/shared/model/transitaire.model';
import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { IClient } from 'app/shared/model/client.model';

export interface IPays {
    id?: number;
    code?: string;
    libelle?: string;
    sites?: ISite[];
    transitaires?: ITransitaire[];
    fournisseurs?: IFournisseur[];
    clients?: IClient[];
}

export class Pays implements IPays {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public sites?: ISite[],
        public transitaires?: ITransitaire[],
        public fournisseurs?: IFournisseur[],
        public clients?: IClient[]
    ) {}
}
