import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { FournisseurService } from './fournisseur.service';

@Component({
    selector: 'jhi-fournisseur-delete-dialog',
    templateUrl: './fournisseur-delete-dialog.component.html'
})
export class FournisseurDeleteDialogComponent {
    fournisseur: IFournisseur;

    constructor(
        protected fournisseurService: FournisseurService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fournisseurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fournisseurListModification',
                content: 'Deleted an fournisseur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-fournisseur-delete-popup',
    template: ''
})
export class FournisseurDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fournisseur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FournisseurDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.fournisseur = fournisseur;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/fournisseur', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/fournisseur', { outlets: { popup: null } }]);
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
