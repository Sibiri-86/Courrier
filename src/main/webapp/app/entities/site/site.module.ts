import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    SiteComponent,
    SiteDetailComponent,
    SiteUpdateComponent,
    SiteDeletePopupComponent,
    SiteDeleteDialogComponent,
    siteRoute,
    sitePopupRoute
} from './';

const ENTITY_STATES = [...siteRoute, ...sitePopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [SiteComponent, SiteDetailComponent, SiteUpdateComponent, SiteDeleteDialogComponent, SiteDeletePopupComponent],
    entryComponents: [SiteComponent, SiteUpdateComponent, SiteDeleteDialogComponent, SiteDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierSiteModule {}
