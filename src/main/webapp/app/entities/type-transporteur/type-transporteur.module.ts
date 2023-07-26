import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    TypeTransporteurComponent,
    TypeTransporteurDetailComponent,
    TypeTransporteurUpdateComponent,
    TypeTransporteurDeletePopupComponent,
    TypeTransporteurDeleteDialogComponent,
    typeTransporteurRoute,
    typeTransporteurPopupRoute
} from './';

const ENTITY_STATES = [...typeTransporteurRoute, ...typeTransporteurPopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeTransporteurComponent,
        TypeTransporteurDetailComponent,
        TypeTransporteurUpdateComponent,
        TypeTransporteurDeleteDialogComponent,
        TypeTransporteurDeletePopupComponent
    ],
    entryComponents: [
        TypeTransporteurComponent,
        TypeTransporteurUpdateComponent,
        TypeTransporteurDeleteDialogComponent,
        TypeTransporteurDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierTypeTransporteurModule {}
