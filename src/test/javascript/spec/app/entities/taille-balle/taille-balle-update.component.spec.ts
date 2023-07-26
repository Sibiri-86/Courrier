/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { TailleBalleUpdateComponent } from 'app/entities/taille-balle/taille-balle-update.component';
import { TailleBalleService } from 'app/entities/taille-balle/taille-balle.service';
import { TailleBalle } from 'app/shared/model/taille-balle.model';

describe('Component Tests', () => {
    describe('TailleBalle Management Update Component', () => {
        let comp: TailleBalleUpdateComponent;
        let fixture: ComponentFixture<TailleBalleUpdateComponent>;
        let service: TailleBalleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TailleBalleUpdateComponent]
            })
                .overrideTemplate(TailleBalleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TailleBalleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TailleBalleService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TailleBalle(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tailleBalle = entity;
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
                    const entity = new TailleBalle();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tailleBalle = entity;
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
