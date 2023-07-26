import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Transitaire } from 'app/shared/model/transitaire.model';
import { TransitaireService } from './transitaire.service';
import { TransitaireComponent } from './transitaire.component';
import { TransitaireDetailComponent } from './transitaire-detail.component';
import { TransitaireUpdateComponent } from './transitaire-update.component';
import { TransitaireDeletePopupComponent } from './transitaire-delete-dialog.component';
import { ITransitaire } from 'app/shared/model/transitaire.model';

@Injectable({ providedIn: 'root' })
export class TransitaireResolve implements Resolve<ITransitaire> {
    constructor(private service: TransitaireService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITransitaire> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Transitaire>) => response.ok),
                map((transitaire: HttpResponse<Transitaire>) => transitaire.body)
            );
        }
        return of(new Transitaire());
    }
}

export const transitaireRoute: Routes = [
    {
        path: '',
        component: TransitaireComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Transitaires'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TransitaireDetailComponent,
        resolve: {
            transitaire: TransitaireResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transitaires'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TransitaireUpdateComponent,
        resolve: {
            transitaire: TransitaireResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transitaires'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TransitaireUpdateComponent,
        resolve: {
            transitaire: TransitaireResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transitaires'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transitairePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TransitaireDeletePopupComponent,
        resolve: {
            transitaire: TransitaireResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transitaires'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
