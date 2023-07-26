import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISite } from 'app/shared/model/site.model';
import { SiteService } from './site.service';
import { IPays } from 'app/shared/model/pays.model';
import { PaysService } from 'app/entities/pays';

@Component({
    selector: 'jhi-site-update',
    templateUrl: './site-update.component.html'
})
export class SiteUpdateComponent implements OnInit {
    site: ISite;
    isSaving: boolean;

    pays: IPays[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected siteService: SiteService,
        protected paysService: PaysService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ site }) => {
            this.site = site;
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
        if (this.site.id !== undefined) {
            this.subscribeToSaveResponse(this.siteService.update(this.site));
        } else {
            this.subscribeToSaveResponse(this.siteService.create(this.site));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISite>>) {
        result.subscribe((res: HttpResponse<ISite>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
