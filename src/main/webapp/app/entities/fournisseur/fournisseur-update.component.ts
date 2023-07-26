import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { FournisseurService } from './fournisseur.service';
import { IPays } from 'app/shared/model/pays.model';
import { PaysService } from 'app/entities/pays';
import { ITypeTransporteur } from 'app/shared/model/type-transporteur.model';
import { TypeTransporteurService } from 'app/entities/type-transporteur';

@Component({
    selector: 'jhi-fournisseur-update',
    templateUrl: './fournisseur-update.component.html'
})
export class FournisseurUpdateComponent implements OnInit {
    fournisseur: IFournisseur;
    isSaving: boolean;

    pays: IPays[];

    typetransporteurs: ITypeTransporteur[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected fournisseurService: FournisseurService,
        protected paysService: PaysService,
        protected typeTransporteurService: TypeTransporteurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fournisseur }) => {
            this.fournisseur = fournisseur;
        });
        this.paysService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IPays[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPays[]>) => response.body)
            )
            .subscribe((res: IPays[]) => (this.pays = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.typeTransporteurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITypeTransporteur[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITypeTransporteur[]>) => response.body)
            )
            .subscribe((res: ITypeTransporteur[]) => (this.typetransporteurs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.fournisseur.id !== undefined) {
            this.subscribeToSaveResponse(this.fournisseurService.update(this.fournisseur));
        } else {
            this.subscribeToSaveResponse(this.fournisseurService.create(this.fournisseur));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFournisseur>>) {
        result.subscribe((res: HttpResponse<IFournisseur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTypeTransporteurById(index: number, item: ITypeTransporteur) {
        return item.id;
    }
}
