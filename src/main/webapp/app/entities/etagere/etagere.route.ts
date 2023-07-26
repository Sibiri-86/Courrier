import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Etagere } from 'app/shared/model/etagere.model';
import { EtagereService } from './etagere.service';
import { EtagereComponent } from './etagere.component';
import { EtagereDetailComponent } from './etagere-detail.component';
import { EtagereUpdateComponent } from './etagere-update.component';
import { EtagereDeletePopupComponent } from './etagere-delete-dialog.component';
import { IEtagere } from 'app/shared/model/etagere.model';

@Injectable({ providedIn: 'root' })
export class EtagereResolve implements Resolve<IEtagere> {
    constructor(private service: EtagereService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEtagere> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Etagere>) => response.ok),
                map((etagere: HttpResponse<Etagere>) => etagere.body)
            );
        }
        return of(new Etagere());
    }
}

export const etagereRoute: Routes = [
    {
        path: '',
        component: EtagereComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Etageres'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EtagereDetailComponent,
        resolve: {
            etagere: EtagereResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Etageres'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EtagereUpdateComponent,
        resolve: {
            etagere: EtagereResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Etageres'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EtagereUpdateComponent,
        resolve: {
            etagere: EtagereResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Etageres'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const etagerePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EtagereDeletePopupComponent,
        resolve: {
            etagere: EtagereResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Etageres'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
