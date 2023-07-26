/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { TypeTransporteurUpdateComponent } from 'app/entities/type-transporteur/type-transporteur-update.component';
import { TypeTransporteurService } from 'app/entities/type-transporteur/type-transporteur.service';
import { TypeTransporteur } from 'app/shared/model/type-transporteur.model';

describe('Component Tests', () => {
    describe('TypeTransporteur Management Update Component', () => {
        let comp: TypeTransporteurUpdateComponent;
        let fixture: ComponentFixture<TypeTransporteurUpdateComponent>;
        let service: TypeTransporteurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TypeTransporteurUpdateComponent]
            })
                .overrideTemplate(TypeTransporteurUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeTransporteurUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeTransporteurService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TypeTransporteur(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.typeTransporteur = entity;
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
                    const entity = new TypeTransporteur();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.typeTransporteur = entity;
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
