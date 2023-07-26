import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TailleBalle } from 'app/shared/model/taille-balle.model';
import { TailleBalleService } from './taille-balle.service';
import { TailleBalleComponent } from './taille-balle.component';
import { TailleBalleDetailComponent } from './taille-balle-detail.component';
import { TailleBalleUpdateComponent } from './taille-balle-update.component';
import { TailleBalleDeletePopupComponent } from './taille-balle-delete-dialog.component';
import { ITailleBalle } from 'app/shared/model/taille-balle.model';

@Injectable({ providedIn: 'root' })
export class TailleBalleResolve implements Resolve<ITailleBalle> {
    constructor(private service: TailleBalleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITailleBalle> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TailleBalle>) => response.ok),
                map((tailleBalle: HttpResponse<TailleBalle>) => tailleBalle.body)
            );
        }
        return of(new TailleBalle());
    }
}

export const tailleBalleRoute: Routes = [
    {
        path: '',
        component: TailleBalleComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'TailleBalles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TailleBalleDetailComponent,
        resolve: {
            tailleBalle: TailleBalleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TailleBalles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TailleBalleUpdateComponent,
        resolve: {
            tailleBalle: TailleBalleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TailleBalles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TailleBalleUpdateComponent,
        resolve: {
            tailleBalle: TailleBalleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TailleBalles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tailleBallePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TailleBalleDeletePopupComponent,
        resolve: {
            tailleBalle: TailleBalleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TailleBalles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
