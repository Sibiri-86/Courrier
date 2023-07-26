import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtagere } from 'app/shared/model/etagere.model';

@Component({
    selector: 'jhi-etagere-detail',
    templateUrl: './etagere-detail.component.html'
})
export class EtagereDetailComponent implements OnInit {
    etagere: IEtagere;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etagere }) => {
            this.etagere = etagere;
        });
    }

    previousState() {
        window.history.back();
    }
}
