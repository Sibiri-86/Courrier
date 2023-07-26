import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITransporteur } from 'app/shared/model/transporteur.model';
import { TransporteurService } from './transporteur.service';

@Component({
    selector: 'jhi-transporteur-update',
    templateUrl: './transporteur-update.component.html'
})
export class TransporteurUpdateComponent implements OnInit {
    transporteur: ITransporteur;
    isSaving: boolean;

    constructor(protected transporteurService: TransporteurService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transporteur }) => {
            this.transporteur = transporteur;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.transporteur.id !== undefined) {
            this.subscribeToSaveResponse(this.transporteurService.update(this.transporteur));
        } else {
            this.subscribeToSaveResponse(this.transporteurService.create(this.transporteur));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransporteur>>) {
        result.subscribe((res: HttpResponse<ITransporteur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
