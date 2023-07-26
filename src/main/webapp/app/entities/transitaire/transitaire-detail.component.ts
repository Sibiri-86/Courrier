import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransitaire } from 'app/shared/model/transitaire.model';

@Component({
    selector: 'jhi-transitaire-detail',
    templateUrl: './transitaire-detail.component.html'
})
export class TransitaireDetailComponent implements OnInit {
    transitaire: ITransitaire;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transitaire }) => {
            this.transitaire = transitaire;
        });
    }

    previousState() {
        window.history.back();
    }
}
