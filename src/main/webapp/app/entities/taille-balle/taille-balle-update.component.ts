import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITailleBalle } from 'app/shared/model/taille-balle.model';
import { TailleBalleService } from './taille-balle.service';

@Component({
    selector: 'jhi-taille-balle-update',
    templateUrl: './taille-balle-update.component.html'
})
export class TailleBalleUpdateComponent implements OnInit {
    tailleBalle: ITailleBalle;
    isSaving: boolean;

    constructor(protected tailleBalleService: TailleBalleService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tailleBalle }) => {
            this.tailleBalle = tailleBalle;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tailleBalle.id !== undefined) {
            this.subscribeToSaveResponse(this.tailleBalleService.update(this.tailleBalle));
        } else {
            this.subscribeToSaveResponse(this.tailleBalleService.create(this.tailleBalle));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITailleBalle>>) {
        result.subscribe((res: HttpResponse<ITailleBalle>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
