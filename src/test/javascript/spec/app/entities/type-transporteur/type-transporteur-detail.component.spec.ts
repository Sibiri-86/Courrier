/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { TypeTransporteurDetailComponent } from 'app/entities/type-transporteur/type-transporteur-detail.component';
import { TypeTransporteur } from 'app/shared/model/type-transporteur.model';

describe('Component Tests', () => {
    describe('TypeTransporteur Management Detail Component', () => {
        let comp: TypeTransporteurDetailComponent;
        let fixture: ComponentFixture<TypeTransporteurDetailComponent>;
        const route = ({ data: of({ typeTransporteur: new TypeTransporteur(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TypeTransporteurDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeTransporteurDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeTransporteurDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeTransporteur).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
