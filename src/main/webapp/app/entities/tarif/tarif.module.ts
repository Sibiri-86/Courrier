import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    TarifComponent,
    TarifDetailComponent,
    TarifUpdateComponent,
    TarifDeletePopupComponent,
    TarifDeleteDialogComponent,
    tarifRoute,
    tarifPopupRoute
} from './';

const ENTITY_STATES = [...tarifRoute, ...tarifPopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [TarifComponent, TarifDetailComponent, TarifUpdateComponent, TarifDeleteDialogComponent, TarifDeletePopupComponent],
    entryComponents: [TarifComponent, TarifUpdateComponent, TarifDeleteDialogComponent, TarifDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierTarifModule {}
