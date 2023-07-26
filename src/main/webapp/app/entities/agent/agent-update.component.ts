import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAgent } from 'app/shared/model/agent.model';
import { AgentService } from './agent.service';
import { ISite } from 'app/shared/model/site.model';
import { SiteService } from 'app/entities/site';

@Component({
    selector: 'jhi-agent-update',
    templateUrl: './agent-update.component.html'
})
export class AgentUpdateComponent implements OnInit {
    agent: IAgent;
    isSaving: boolean;

    sites: ISite[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected agentService: AgentService,
        protected siteService: SiteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ agent }) => {
            this.agent = agent;
        });
        this.siteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISite[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISite[]>) => response.body)
            )
            .subscribe((res: ISite[]) => (this.sites = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.agent.id !== undefined) {
            this.subscribeToSaveResponse(this.agentService.update(this.agent));
        } else {
            this.subscribeToSaveResponse(this.agentService.create(this.agent));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgent>>) {
        result.subscribe((res: HttpResponse<IAgent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSiteById(index: number, item: ISite) {
        return item.id;
    }
}
