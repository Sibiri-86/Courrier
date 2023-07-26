import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    TailleBalleComponent,
    TailleBalleDetailComponent,
    TailleBalleUpdateComponent,
    TailleBalleDeletePopupComponent,
    TailleBalleDeleteDialogComponent,
    tailleBalleRoute,
    tailleBallePopupRoute
} from './';

const ENTITY_STATES = [...tailleBalleRoute, ...tailleBallePopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TailleBalleComponent,
        TailleBalleDetailComponent,
        TailleBalleUpdateComponent,
        TailleBalleDeleteDialogComponent,
        TailleBalleDeletePopupComponent
    ],
    entryComponents: [TailleBalleComponent, TailleBalleUpdateComponent, TailleBalleDeleteDialogComponent, TailleBalleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierTailleBalleModule {}
