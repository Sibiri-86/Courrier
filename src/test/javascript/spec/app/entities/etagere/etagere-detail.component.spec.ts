/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { EtagereDetailComponent } from 'app/entities/etagere/etagere-detail.component';
import { Etagere } from 'app/shared/model/etagere.model';

describe('Component Tests', () => {
    describe('Etagere Management Detail Component', () => {
        let comp: EtagereDetailComponent;
        let fixture: ComponentFixture<EtagereDetailComponent>;
        const route = ({ data: of({ etagere: new Etagere(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [EtagereDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EtagereDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EtagereDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.etagere).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
