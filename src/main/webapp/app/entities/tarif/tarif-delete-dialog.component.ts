import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITarif } from 'app/shared/model/tarif.model';
import { TarifService } from './tarif.service';

@Component({
    selector: 'jhi-tarif-delete-dialog',
    templateUrl: './tarif-delete-dialog.component.html'
})
export class TarifDeleteDialogComponent {
    tarif: ITarif;

    constructor(protected tarifService: TarifService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tarifService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tarifListModification',
                content: 'Deleted an tarif'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tarif-delete-popup',
    template: ''
})
export class TarifDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tarif }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TarifDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tarif = tarif;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/tarif', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/tarif', { outlets: { popup: null } }]);
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
