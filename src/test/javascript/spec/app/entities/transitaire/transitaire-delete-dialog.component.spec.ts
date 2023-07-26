/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CourrierTestModule } from '../../../test.module';
import { TransitaireDeleteDialogComponent } from 'app/entities/transitaire/transitaire-delete-dialog.component';
import { TransitaireService } from 'app/entities/transitaire/transitaire.service';

describe('Component Tests', () => {
    describe('Transitaire Management Delete Component', () => {
        let comp: TransitaireDeleteDialogComponent;
        let fixture: ComponentFixture<TransitaireDeleteDialogComponent>;
        let service: TransitaireService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TransitaireDeleteDialogComponent]
            })
                .overrideTemplate(TransitaireDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransitaireDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransitaireService);
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
