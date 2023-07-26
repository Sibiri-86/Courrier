import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeTransporteur } from 'app/shared/model/type-transporteur.model';

@Component({
    selector: 'jhi-type-transporteur-detail',
    templateUrl: './type-transporteur-detail.component.html'
})
export class TypeTransporteurDetailComponent implements OnInit {
    typeTransporteur: ITypeTransporteur;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeTransporteur }) => {
            this.typeTransporteur = typeTransporteur;
        });
    }

    previousState() {
        window.history.back();
    }
}
