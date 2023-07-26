/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { NatureMarchandDetailComponent } from 'app/entities/nature-marchand/nature-marchand-detail.component';
import { NatureMarchand } from 'app/shared/model/nature-marchand.model';

describe('Component Tests', () => {
    describe('NatureMarchand Management Detail Component', () => {
        let comp: NatureMarchandDetailComponent;
        let fixture: ComponentFixture<NatureMarchandDetailComponent>;
        const route = ({ data: of({ natureMarchand: new NatureMarchand(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [NatureMarchandDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NatureMarchandDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NatureMarchandDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.natureMarchand).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
