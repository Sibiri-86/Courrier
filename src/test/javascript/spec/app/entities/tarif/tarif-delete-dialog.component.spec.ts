/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CourrierTestModule } from '../../../test.module';
import { TarifDeleteDialogComponent } from 'app/entities/tarif/tarif-delete-dialog.component';
import { TarifService } from 'app/entities/tarif/tarif.service';

describe('Component Tests', () => {
    describe('Tarif Management Delete Component', () => {
        let comp: TarifDeleteDialogComponent;
        let fixture: ComponentFixture<TarifDeleteDialogComponent>;
        let service: TarifService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TarifDeleteDialogComponent]
            })
                .overrideTemplate(TarifDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TarifDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TarifService);
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
