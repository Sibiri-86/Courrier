import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Tarif } from 'app/shared/model/tarif.model';
import { TarifService } from './tarif.service';
import { TarifComponent } from './tarif.component';
import { TarifDetailComponent } from './tarif-detail.component';
import { TarifUpdateComponent } from './tarif-update.component';
import { TarifDeletePopupComponent } from './tarif-delete-dialog.component';
import { ITarif } from 'app/shared/model/tarif.model';

@Injectable({ providedIn: 'root' })
export class TarifResolve implements Resolve<ITarif> {
    constructor(private service: TarifService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITarif> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Tarif>) => response.ok),
                map((tarif: HttpResponse<Tarif>) => tarif.body)
            );
        }
        return of(new Tarif());
    }
}

export const tarifRoute: Routes = [
    {
        path: '',
        component: TarifComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Tarifs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TarifDetailComponent,
        resolve: {
            tarif: TarifResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tarifs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TarifUpdateComponent,
        resolve: {
            tarif: TarifResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tarifs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TarifUpdateComponent,
        resolve: {
            tarif: TarifResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tarifs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tarifPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TarifDeletePopupComponent,
        resolve: {
            tarif: TarifResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tarifs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
