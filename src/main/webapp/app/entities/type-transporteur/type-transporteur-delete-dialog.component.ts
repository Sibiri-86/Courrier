import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeTransporteur } from 'app/shared/model/type-transporteur.model';
import { TypeTransporteurService } from './type-transporteur.service';

@Component({
    selector: 'jhi-type-transporteur-delete-dialog',
    templateUrl: './type-transporteur-delete-dialog.component.html'
})
export class TypeTransporteurDeleteDialogComponent {
    typeTransporteur: ITypeTransporteur;

    constructor(
        protected typeTransporteurService: TypeTransporteurService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeTransporteurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeTransporteurListModification',
                content: 'Deleted an typeTransporteur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-transporteur-delete-popup',
    template: ''
})
export class TypeTransporteurDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeTransporteur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeTransporteurDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.typeTransporteur = typeTransporteur;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/type-transporteur', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/type-transporteur', { outlets: { popup: null } }]);
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
