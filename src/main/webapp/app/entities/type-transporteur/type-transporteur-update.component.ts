import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITypeTransporteur } from 'app/shared/model/type-transporteur.model';
import { TypeTransporteurService } from './type-transporteur.service';

@Component({
    selector: 'jhi-type-transporteur-update',
    templateUrl: './type-transporteur-update.component.html'
})
export class TypeTransporteurUpdateComponent implements OnInit {
    typeTransporteur: ITypeTransporteur;
    isSaving: boolean;

    constructor(protected typeTransporteurService: TypeTransporteurService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeTransporteur }) => {
            this.typeTransporteur = typeTransporteur;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.typeTransporteur.id !== undefined) {
            this.subscribeToSaveResponse(this.typeTransporteurService.update(this.typeTransporteur));
        } else {
            this.subscribeToSaveResponse(this.typeTransporteurService.create(this.typeTransporteur));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeTransporteur>>) {
        result.subscribe((res: HttpResponse<ITypeTransporteur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
