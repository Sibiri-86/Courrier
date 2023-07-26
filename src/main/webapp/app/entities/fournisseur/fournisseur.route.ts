import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Fournisseur } from 'app/shared/model/fournisseur.model';
import { FournisseurService } from './fournisseur.service';
import { FournisseurComponent } from './fournisseur.component';
import { FournisseurDetailComponent } from './fournisseur-detail.component';
import { FournisseurUpdateComponent } from './fournisseur-update.component';
import { FournisseurDeletePopupComponent } from './fournisseur-delete-dialog.component';
import { IFournisseur } from 'app/shared/model/fournisseur.model';

@Injectable({ providedIn: 'root' })
export class FournisseurResolve implements Resolve<IFournisseur> {
    constructor(private service: FournisseurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFournisseur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Fournisseur>) => response.ok),
                map((fournisseur: HttpResponse<Fournisseur>) => fournisseur.body)
            );
        }
        return of(new Fournisseur());
    }
}

export const fournisseurRoute: Routes = [
    {
        path: '',
        component: FournisseurComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Fournisseurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: FournisseurDetailComponent,
        resolve: {
            fournisseur: FournisseurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Fournisseurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: FournisseurUpdateComponent,
        resolve: {
            fournisseur: FournisseurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Fournisseurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: FournisseurUpdateComponent,
        resolve: {
            fournisseur: FournisseurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Fournisseurs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fournisseurPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: FournisseurDeletePopupComponent,
        resolve: {
            fournisseur: FournisseurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Fournisseurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
