/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { TransitaireUpdateComponent } from 'app/entities/transitaire/transitaire-update.component';
import { TransitaireService } from 'app/entities/transitaire/transitaire.service';
import { Transitaire } from 'app/shared/model/transitaire.model';

describe('Component Tests', () => {
    describe('Transitaire Management Update Component', () => {
        let comp: TransitaireUpdateComponent;
        let fixture: ComponentFixture<TransitaireUpdateComponent>;
        let service: TransitaireService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TransitaireUpdateComponent]
            })
                .overrideTemplate(TransitaireUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransitaireUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransitaireService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Transitaire(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transitaire = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Transitaire();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.transitaire = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
