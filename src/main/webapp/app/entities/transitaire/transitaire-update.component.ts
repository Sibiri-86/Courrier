import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITransitaire } from 'app/shared/model/transitaire.model';
import { TransitaireService } from './transitaire.service';
import { IPays } from 'app/shared/model/pays.model';
import { PaysService } from 'app/entities/pays';

@Component({
    selector: 'jhi-transitaire-update',
    templateUrl: './transitaire-update.component.html'
})
export class TransitaireUpdateComponent implements OnInit {
    transitaire: ITransitaire;
    isSaving: boolean;

    pays: IPays[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected transitaireService: TransitaireService,
        protected paysService: PaysService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transitaire }) => {
            this.transitaire = transitaire;
        });
        this.paysService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IPays[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPays[]>) => response.body)
            )
            .subscribe((res: IPays[]) => (this.pays = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.transitaire.id !== undefined) {
            this.subscribeToSaveResponse(this.transitaireService.update(this.transitaire));
        } else {
            this.subscribeToSaveResponse(this.transitaireService.create(this.transitaire));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransitaire>>) {
        result.subscribe((res: HttpResponse<ITransitaire>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPaysById(index: number, item: IPays) {
        return item.id;
    }
}
