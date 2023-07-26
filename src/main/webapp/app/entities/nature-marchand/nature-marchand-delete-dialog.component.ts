import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INatureMarchand } from 'app/shared/model/nature-marchand.model';
import { NatureMarchandService } from './nature-marchand.service';

@Component({
    selector: 'jhi-nature-marchand-delete-dialog',
    templateUrl: './nature-marchand-delete-dialog.component.html'
})
export class NatureMarchandDeleteDialogComponent {
    natureMarchand: INatureMarchand;

    constructor(
        protected natureMarchandService: NatureMarchandService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.natureMarchandService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'natureMarchandListModification',
                content: 'Deleted an natureMarchand'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nature-marchand-delete-popup',
    template: ''
})
export class NatureMarchandDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ natureMarchand }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NatureMarchandDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.natureMarchand = natureMarchand;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/nature-marchand', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/nature-marchand', { outlets: { popup: null } }]);
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
