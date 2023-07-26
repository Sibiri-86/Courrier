import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    EtagereComponent,
    EtagereDetailComponent,
    EtagereUpdateComponent,
    EtagereDeletePopupComponent,
    EtagereDeleteDialogComponent,
    etagereRoute,
    etagerePopupRoute
} from './';

const ENTITY_STATES = [...etagereRoute, ...etagerePopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EtagereComponent,
        EtagereDetailComponent,
        EtagereUpdateComponent,
        EtagereDeleteDialogComponent,
        EtagereDeletePopupComponent
    ],
    entryComponents: [EtagereComponent, EtagereUpdateComponent, EtagereDeleteDialogComponent, EtagereDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierEtagereModule {}
