import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITailleBalle } from 'app/shared/model/taille-balle.model';
import { TailleBalleService } from './taille-balle.service';

@Component({
    selector: 'jhi-taille-balle-delete-dialog',
    templateUrl: './taille-balle-delete-dialog.component.html'
})
export class TailleBalleDeleteDialogComponent {
    tailleBalle: ITailleBalle;

    constructor(
        protected tailleBalleService: TailleBalleService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tailleBalleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tailleBalleListModification',
                content: 'Deleted an tailleBalle'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-taille-balle-delete-popup',
    template: ''
})
export class TailleBalleDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tailleBalle }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TailleBalleDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tailleBalle = tailleBalle;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/taille-balle', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/taille-balle', { outlets: { popup: null } }]);
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
