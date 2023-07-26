import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITarif } from 'app/shared/model/tarif.model';

@Component({
    selector: 'jhi-tarif-detail',
    templateUrl: './tarif-detail.component.html'
})
export class TarifDetailComponent implements OnInit {
    tarif: ITarif;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tarif }) => {
            this.tarif = tarif;
        });
    }

    previousState() {
        window.history.back();
    }
}
