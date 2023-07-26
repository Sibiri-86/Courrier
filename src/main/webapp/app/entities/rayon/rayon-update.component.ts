import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRayon } from 'app/shared/model/rayon.model';
import { RayonService } from './rayon.service';

@Component({
    selector: 'jhi-rayon-update',
    templateUrl: './rayon-update.component.html'
})
export class RayonUpdateComponent implements OnInit {
    rayon: IRayon;
    isSaving: boolean;

    constructor(protected rayonService: RayonService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rayon }) => {
            this.rayon = rayon;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.rayon.id !== undefined) {
            this.subscribeToSaveResponse(this.rayonService.update(this.rayon));
        } else {
            this.subscribeToSaveResponse(this.rayonService.create(this.rayon));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRayon>>) {
        result.subscribe((res: HttpResponse<IRayon>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
