import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INatureMarchand } from 'app/shared/model/nature-marchand.model';

@Component({
    selector: 'jhi-nature-marchand-detail',
    templateUrl: './nature-marchand-detail.component.html'
})
export class NatureMarchandDetailComponent implements OnInit {
    natureMarchand: INatureMarchand;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ natureMarchand }) => {
            this.natureMarchand = natureMarchand;
        });
    }

    previousState() {
        window.history.back();
    }
}
