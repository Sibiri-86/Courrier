import { NgModule } from '@angular/core';

import { CourrierSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [CourrierSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [CourrierSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class CourrierSharedCommonModule {}
