import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourrierSharedModule } from 'app/shared';
import {
    RayonComponent,
    RayonDetailComponent,
    RayonUpdateComponent,
    RayonDeletePopupComponent,
    RayonDeleteDialogComponent,
    rayonRoute,
    rayonPopupRoute
} from './';

const ENTITY_STATES = [...rayonRoute, ...rayonPopupRoute];

@NgModule({
    imports: [CourrierSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [RayonComponent, RayonDetailComponent, RayonUpdateComponent, RayonDeleteDialogComponent, RayonDeletePopupComponent],
    entryComponents: [RayonComponent, RayonUpdateComponent, RayonDeleteDialogComponent, RayonDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierRayonModule {}
