/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { TailleBalleDetailComponent } from 'app/entities/taille-balle/taille-balle-detail.component';
import { TailleBalle } from 'app/shared/model/taille-balle.model';

describe('Component Tests', () => {
    describe('TailleBalle Management Detail Component', () => {
        let comp: TailleBalleDetailComponent;
        let fixture: ComponentFixture<TailleBalleDetailComponent>;
        const route = ({ data: of({ tailleBalle: new TailleBalle(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TailleBalleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TailleBalleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TailleBalleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tailleBalle).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
