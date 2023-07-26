import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEtagere } from 'app/shared/model/etagere.model';
import { EtagereService } from './etagere.service';

@Component({
    selector: 'jhi-etagere-update',
    templateUrl: './etagere-update.component.html'
})
export class EtagereUpdateComponent implements OnInit {
    etagere: IEtagere;
    isSaving: boolean;

    constructor(protected etagereService: EtagereService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ etagere }) => {
            this.etagere = etagere;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.etagere.id !== undefined) {
            this.subscribeToSaveResponse(this.etagereService.update(this.etagere));
        } else {
            this.subscribeToSaveResponse(this.etagereService.create(this.etagere));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtagere>>) {
        result.subscribe((res: HttpResponse<IEtagere>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
