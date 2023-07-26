import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { INatureMarchand } from 'app/shared/model/nature-marchand.model';
import { NatureMarchandService } from './nature-marchand.service';

@Component({
    selector: 'jhi-nature-marchand-update',
    templateUrl: './nature-marchand-update.component.html'
})
export class NatureMarchandUpdateComponent implements OnInit {
    natureMarchand: INatureMarchand;
    isSaving: boolean;

    constructor(protected natureMarchandService: NatureMarchandService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ natureMarchand }) => {
            this.natureMarchand = natureMarchand;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.natureMarchand.id !== undefined) {
            this.subscribeToSaveResponse(this.natureMarchandService.update(this.natureMarchand));
        } else {
            this.subscribeToSaveResponse(this.natureMarchandService.create(this.natureMarchand));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INatureMarchand>>) {
        result.subscribe((res: HttpResponse<INatureMarchand>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
