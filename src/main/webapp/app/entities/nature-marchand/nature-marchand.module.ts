import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    NatureMarchandComponent,
    NatureMarchandDetailComponent,
    NatureMarchandUpdateComponent,
    NatureMarchandDeletePopupComponent,
    NatureMarchandDeleteDialogComponent,
    natureMarchandRoute,
    natureMarchandPopupRoute
} from './';

const ENTITY_STATES = [...natureMarchandRoute, ...natureMarchandPopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NatureMarchandComponent,
        NatureMarchandDetailComponent,
        NatureMarchandUpdateComponent,
        NatureMarchandDeleteDialogComponent,
        NatureMarchandDeletePopupComponent
    ],
    entryComponents: [
        NatureMarchandComponent,
        NatureMarchandUpdateComponent,
        NatureMarchandDeleteDialogComponent,
        NatureMarchandDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierNatureMarchandModule {}
