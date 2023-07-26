import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITailleBalle } from 'app/shared/model/taille-balle.model';

@Component({
    selector: 'jhi-taille-balle-detail',
    templateUrl: './taille-balle-detail.component.html'
})
export class TailleBalleDetailComponent implements OnInit {
    tailleBalle: ITailleBalle;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tailleBalle }) => {
            this.tailleBalle = tailleBalle;
        });
    }

    previousState() {
        window.history.back();
    }
}
