/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CourrierTestModule } from '../../../test.module';
import { NatureMarchandDeleteDialogComponent } from 'app/entities/nature-marchand/nature-marchand-delete-dialog.component';
import { NatureMarchandService } from 'app/entities/nature-marchand/nature-marchand.service';

describe('Component Tests', () => {
    describe('NatureMarchand Management Delete Component', () => {
        let comp: NatureMarchandDeleteDialogComponent;
        let fixture: ComponentFixture<NatureMarchandDeleteDialogComponent>;
        let service: NatureMarchandService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [NatureMarchandDeleteDialogComponent]
            })
                .overrideTemplate(NatureMarchandDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NatureMarchandDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NatureMarchandService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
