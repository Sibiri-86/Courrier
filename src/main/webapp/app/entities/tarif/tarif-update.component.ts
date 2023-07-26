import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITarif } from 'app/shared/model/tarif.model';
import { TarifService } from './tarif.service';
import { ITailleBalle } from 'app/shared/model/taille-balle.model';
import { TailleBalleService } from 'app/entities/taille-balle';

@Component({
    selector: 'jhi-tarif-update',
    templateUrl: './tarif-update.component.html'
})
export class TarifUpdateComponent implements OnInit {
    tarif: ITarif;
    isSaving: boolean;

    tailleballes: ITailleBalle[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected tarifService: TarifService,
        protected tailleBalleService: TailleBalleService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tarif }) => {
            this.tarif = tarif;
        });
        this.tailleBalleService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITailleBalle[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITailleBalle[]>) => response.body)
            )
            .subscribe((res: ITailleBalle[]) => (this.tailleballes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tarif.id !== undefined) {
            this.subscribeToSaveResponse(this.tarifService.update(this.tarif));
        } else {
            this.subscribeToSaveResponse(this.tarifService.create(this.tarif));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITarif>>) {
        result.subscribe((res: HttpResponse<ITarif>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTailleBalleById(index: number, item: ITailleBalle) {
        return item.id;
    }
}
