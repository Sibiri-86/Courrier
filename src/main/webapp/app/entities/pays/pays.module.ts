import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    PaysComponent,
    PaysDetailComponent,
    PaysUpdateComponent,
    PaysDeletePopupComponent,
    PaysDeleteDialogComponent,
    paysRoute,
    paysPopupRoute
} from './';

const ENTITY_STATES = [...paysRoute, ...paysPopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PaysComponent, PaysDetailComponent, PaysUpdateComponent, PaysDeleteDialogComponent, PaysDeletePopupComponent],
    entryComponents: [PaysComponent, PaysUpdateComponent, PaysDeleteDialogComponent, PaysDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierPaysModule {}
