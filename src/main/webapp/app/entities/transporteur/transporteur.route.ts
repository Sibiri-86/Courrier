import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Transporteur } from 'app/shared/model/transporteur.model';
import { TransporteurService } from './transporteur.service';
import { TransporteurComponent } from './transporteur.component';
import { TransporteurDetailComponent } from './transporteur-detail.component';
import { TransporteurUpdateComponent } from './transporteur-update.component';
import { TransporteurDeletePopupComponent } from './transporteur-delete-dialog.component';
import { ITransporteur } from 'app/shared/model/transporteur.model';

@Injectable({ providedIn: 'root' })
export class TransporteurResolve implements Resolve<ITransporteur> {
    constructor(private service: TransporteurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITransporteur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Transporteur>) => response.ok),
                map((transporteur: HttpResponse<Transporteur>) => transporteur.body)
            );
        }
        return of(new Transporteur());
    }
}

export const transporteurRoute: Routes = [
    {
        path: '',
        component: TransporteurComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Transporteurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TransporteurDetailComponent,
        resolve: {
            transporteur: TransporteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transporteurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TransporteurUpdateComponent,
        resolve: {
            transporteur: TransporteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transporteurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TransporteurUpdateComponent,
        resolve: {
            transporteur: TransporteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transporteurs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transporteurPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TransporteurDeletePopupComponent,
        resolve: {
            transporteur: TransporteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transporteurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
