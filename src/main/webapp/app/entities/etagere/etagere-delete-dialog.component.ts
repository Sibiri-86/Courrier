import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtagere } from 'app/shared/model/etagere.model';
import { EtagereService } from './etagere.service';

@Component({
    selector: 'jhi-etagere-delete-dialog',
    templateUrl: './etagere-delete-dialog.component.html'
})
export class EtagereDeleteDialogComponent {
    etagere: IEtagere;

    constructor(protected etagereService: EtagereService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.etagereService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'etagereListModification',
                content: 'Deleted an etagere'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-etagere-delete-popup',
    template: ''
})
export class EtagereDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etagere }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EtagereDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.etagere = etagere;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/etagere', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/etagere', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
