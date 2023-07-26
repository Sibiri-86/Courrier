/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CourrierTestModule } from '../../../test.module';
import { NatureMarchandUpdateComponent } from 'app/entities/nature-marchand/nature-marchand-update.component';
import { NatureMarchandService } from 'app/entities/nature-marchand/nature-marchand.service';
import { NatureMarchand } from 'app/shared/model/nature-marchand.model';

describe('Component Tests', () => {
    describe('NatureMarchand Management Update Component', () => {
        let comp: NatureMarchandUpdateComponent;
        let fixture: ComponentFixture<NatureMarchandUpdateComponent>;
        let service: NatureMarchandService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [NatureMarchandUpdateComponent]
            })
                .overrideTemplate(NatureMarchandUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NatureMarchandUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NatureMarchandService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NatureMarchand(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.natureMarchand = entity;
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
                    const entity = new NatureMarchand();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.natureMarchand = entity;
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
