/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CourrierTestModule } from '../../../test.module';
import { TailleBalleDeleteDialogComponent } from 'app/entities/taille-balle/taille-balle-delete-dialog.component';
import { TailleBalleService } from 'app/entities/taille-balle/taille-balle.service';

describe('Component Tests', () => {
    describe('TailleBalle Management Delete Component', () => {
        let comp: TailleBalleDeleteDialogComponent;
        let fixture: ComponentFixture<TailleBalleDeleteDialogComponent>;
        let service: TailleBalleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CourrierTestModule],
                declarations: [TailleBalleDeleteDialogComponent]
            })
                .overrideTemplate(TailleBalleDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TailleBalleDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TailleBalleService);
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
