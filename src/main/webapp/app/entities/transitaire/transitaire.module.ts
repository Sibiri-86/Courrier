import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    TransitaireComponent,
    TransitaireDetailComponent,
    TransitaireUpdateComponent,
    TransitaireDeletePopupComponent,
    TransitaireDeleteDialogComponent,
    transitaireRoute,
    transitairePopupRoute
} from './';

const ENTITY_STATES = [...transitaireRoute, ...transitairePopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransitaireComponent,
        TransitaireDetailComponent,
        TransitaireUpdateComponent,
        TransitaireDeleteDialogComponent,
        TransitaireDeletePopupComponent
    ],
    entryComponents: [TransitaireComponent, TransitaireUpdateComponent, TransitaireDeleteDialogComponent, TransitaireDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierTransitaireModule {}
