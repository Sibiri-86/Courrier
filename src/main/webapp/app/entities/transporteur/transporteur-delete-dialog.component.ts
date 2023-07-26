import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransporteur } from 'app/shared/model/transporteur.model';
import { TransporteurService } from './transporteur.service';

@Component({
    selector: 'jhi-transporteur-delete-dialog',
    templateUrl: './transporteur-delete-dialog.component.html'
})
export class TransporteurDeleteDialogComponent {
    transporteur: ITransporteur;

    constructor(
        protected transporteurService: TransporteurService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.transporteurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'transporteurListModification',
                content: 'Deleted an transporteur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-transporteur-delete-popup',
    template: ''
})
export class TransporteurDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transporteur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TransporteurDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.transporteur = transporteur;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/transporteur', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/transporteur', { outlets: { popup: null } }]);
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
