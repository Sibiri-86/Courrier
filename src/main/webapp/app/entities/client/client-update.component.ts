import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { IPays } from 'app/shared/model/pays.model';
import { PaysService } from 'app/entities/pays';

@Component({
    selector: 'jhi-client-update',
    templateUrl: './client-update.component.html'
})
export class ClientUpdateComponent implements OnInit {
    client: IClient;
    isSaving: boolean;

    pays: IPays[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected clientService: ClientService,
        protected paysService: PaysService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ client }) => {
            this.client = client;
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
        if (this.client.id !== undefined) {
            this.subscribeToSaveResponse(this.clientService.update(this.client));
        } else {
            this.subscribeToSaveResponse(this.clientService.create(this.client));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>) {
        result.subscribe((res: HttpResponse<IClient>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
