/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CourrierTestModule } from '../../../test.module';
import { TypeTransporteurDeleteDialogComponent } from 'app/entities/type-transporteur/type-transporteur-delete-dialog.component';
import { TypeTransporteurService } from 'app/entities/type-transporteur/type-transporteur.service';

describe('Component Tests', () => {
    describe('TypeTransporteur Management Delete Component', () => {
        let comp: TypeTransporteurDeleteDialogComponent;
        let fixture: ComponentFixture<TypeTransporteurDeleteDialogComponent>;
        let service: TypeTransporteurService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TypeTransporteurDeleteDialogComponent]
            })
                .overrideTemplate(TypeTransporteurDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeTransporteurDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeTransporteurService);
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
