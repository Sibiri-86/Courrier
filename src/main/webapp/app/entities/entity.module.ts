import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'pays',
                loadChildren: './pays/pays.module#CourrierPaysModule'
            },
            {
                path: 'site',
                loadChildren: './site/site.module#CourrierSiteModule'
            },
            {
                path: 'agent',
                loadChildren: './agent/agent.module#CourrierAgentModule'
            },
            {
                path: 'transitaire',
                loadChildren: './transitaire/transitaire.module#CourrierTransitaireModule'
            },
            {
                path: 'fournisseur',
                loadChildren: './fournisseur/fournisseur.module#CourrierFournisseurModule'
            },
            {
                path: 'client',
                loadChildren: './client/client.module#CourrierClientModule'
            },
            {
                path: 'type-transporteur',
                loadChildren: './type-transporteur/type-transporteur.module#CourrierTypeTransporteurModule'
            },
            {
                path: 'transporteur',
                loadChildren: './transporteur/transporteur.module#CourrierTransporteurModule'
            },
            {
                path: 'nature-marchand',
                loadChildren: './nature-marchand/nature-marchand.module#CourrierNatureMarchandModule'
            },
            {
                path: 'taille-balle',
                loadChildren: './taille-balle/taille-balle.module#CourrierTailleBalleModule'
            },
            {
                path: 'etagere',
                loadChildren: './etagere/etagere.module#CourrierEtagereModule'
            },
            {
                path: 'rayon',
                loadChildren: './rayon/rayon.module#CourrierRayonModule'
            },
            {
                path: 'tarif',
                loadChildren: './tarif/tarif.module#CourrierTarifModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CourrierEntityModule {}
