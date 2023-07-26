import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    TransporteurComponent,
    TransporteurDetailComponent,
    TransporteurUpdateComponent,
    TransporteurDeletePopupComponent,
    TransporteurDeleteDialogComponent,
    transporteurRoute,
    transporteurPopupRoute
} from './';

const ENTITY_STATES = [...transporteurRoute, ...transporteurPopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransporteurComponent,
        TransporteurDetailComponent,
        TransporteurUpdateComponent,
        TransporteurDeleteDialogComponent,
        TransporteurDeletePopupComponent
    ],
    entryComponents: [
        TransporteurComponent,
        TransporteurUpdateComponent,
        TransporteurDeleteDialogComponent,
        TransporteurDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierTransporteurModule {}
