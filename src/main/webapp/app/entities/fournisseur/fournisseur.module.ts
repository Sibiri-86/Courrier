import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    FournisseurComponent,
    FournisseurDetailComponent,
    FournisseurUpdateComponent,
    FournisseurDeletePopupComponent,
    FournisseurDeleteDialogComponent,
    fournisseurRoute,
    fournisseurPopupRoute
} from './';

const ENTITY_STATES = [...fournisseurRoute, ...fournisseurPopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FournisseurComponent,
        FournisseurDetailComponent,
        FournisseurUpdateComponent,
        FournisseurDeleteDialogComponent,
        FournisseurDeletePopupComponent
    ],
    entryComponents: [FournisseurComponent, FournisseurUpdateComponent, FournisseurDeleteDialogComponent, FournisseurDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierFournisseurModule {}
