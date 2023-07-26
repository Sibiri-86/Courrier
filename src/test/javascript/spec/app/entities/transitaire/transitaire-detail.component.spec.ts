/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { TransitaireDetailComponent } from 'app/entities/transitaire/transitaire-detail.component';
import { Transitaire } from 'app/shared/model/transitaire.model';

describe('Component Tests', () => {
    describe('Transitaire Management Detail Component', () => {
        let comp: TransitaireDetailComponent;
        let fixture: ComponentFixture<TransitaireDetailComponent>;
        const route = ({ data: of({ transitaire: new Transitaire(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TransitaireDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TransitaireDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransitaireDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.transitaire).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
