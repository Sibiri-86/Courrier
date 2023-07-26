import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransitaire } from 'app/shared/model/transitaire.model';
import { TransitaireService } from './transitaire.service';

@Component({
    selector: 'jhi-transitaire-delete-dialog',
    templateUrl: './transitaire-delete-dialog.component.html'
})
export class TransitaireDeleteDialogComponent {
    transitaire: ITransitaire;

    constructor(
        protected transitaireService: TransitaireService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.transitaireService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'transitaireListModification',
                content: 'Deleted an transitaire'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-transitaire-delete-popup',
    template: ''
})
export class TransitaireDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transitaire }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TransitaireDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.transitaire = transitaire;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/transitaire', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/transitaire', { outlets: { popup: null } }]);
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
