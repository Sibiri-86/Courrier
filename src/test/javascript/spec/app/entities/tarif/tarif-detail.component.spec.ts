/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { TarifDetailComponent } from 'app/entities/tarif/tarif-detail.component';
import { Tarif } from 'app/shared/model/tarif.model';

describe('Component Tests', () => {
    describe('Tarif Management Detail Component', () => {
        let comp: TarifDetailComponent;
        let fixture: ComponentFixture<TarifDetailComponent>;
        const route = ({ data: of({ tarif: new Tarif(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TarifDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TarifDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TarifDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tarif).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
