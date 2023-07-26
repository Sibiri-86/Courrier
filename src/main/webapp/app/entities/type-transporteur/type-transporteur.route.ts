import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeTransporteur } from 'app/shared/model/type-transporteur.model';
import { TypeTransporteurService } from './type-transporteur.service';
import { TypeTransporteurComponent } from './type-transporteur.component';
import { TypeTransporteurDetailComponent } from './type-transporteur-detail.component';
import { TypeTransporteurUpdateComponent } from './type-transporteur-update.component';
import { TypeTransporteurDeletePopupComponent } from './type-transporteur-delete-dialog.component';
import { ITypeTransporteur } from 'app/shared/model/type-transporteur.model';

@Injectable({ providedIn: 'root' })
export class TypeTransporteurResolve implements Resolve<ITypeTransporteur> {
    constructor(private service: TypeTransporteurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITypeTransporteur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TypeTransporteur>) => response.ok),
                map((typeTransporteur: HttpResponse<TypeTransporteur>) => typeTransporteur.body)
            );
        }
        return of(new TypeTransporteur());
    }
}

export const typeTransporteurRoute: Routes = [
    {
        path: '',
        component: TypeTransporteurComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'TypeTransporteurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TypeTransporteurDetailComponent,
        resolve: {
            typeTransporteur: TypeTransporteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeTransporteurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TypeTransporteurUpdateComponent,
        resolve: {
            typeTransporteur: TypeTransporteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeTransporteurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TypeTransporteurUpdateComponent,
        resolve: {
            typeTransporteur: TypeTransporteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeTransporteurs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeTransporteurPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TypeTransporteurDeletePopupComponent,
        resolve: {
            typeTransporteur: TypeTransporteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeTransporteurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
