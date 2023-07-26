import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NatureMarchand } from 'app/shared/model/nature-marchand.model';
import { NatureMarchandService } from './nature-marchand.service';
import { NatureMarchandComponent } from './nature-marchand.component';
import { NatureMarchandDetailComponent } from './nature-marchand-detail.component';
import { NatureMarchandUpdateComponent } from './nature-marchand-update.component';
import { NatureMarchandDeletePopupComponent } from './nature-marchand-delete-dialog.component';
import { INatureMarchand } from 'app/shared/model/nature-marchand.model';

@Injectable({ providedIn: 'root' })
export class NatureMarchandResolve implements Resolve<INatureMarchand> {
    constructor(private service: NatureMarchandService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<INatureMarchand> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NatureMarchand>) => response.ok),
                map((natureMarchand: HttpResponse<NatureMarchand>) => natureMarchand.body)
            );
        }
        return of(new NatureMarchand());
    }
}

export const natureMarchandRoute: Routes = [
    {
        path: '',
        component: NatureMarchandComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'NatureMarchands'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: NatureMarchandDetailComponent,
        resolve: {
            natureMarchand: NatureMarchandResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'NatureMarchands'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: NatureMarchandUpdateComponent,
        resolve: {
            natureMarchand: NatureMarchandResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'NatureMarchands'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: NatureMarchandUpdateComponent,
        resolve: {
            natureMarchand: NatureMarchandResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'NatureMarchands'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const natureMarchandPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: NatureMarchandDeletePopupComponent,
        resolve: {
            natureMarchand: NatureMarchandResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'NatureMarchands'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
