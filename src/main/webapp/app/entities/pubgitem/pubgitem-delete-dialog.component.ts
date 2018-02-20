import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Pubgitem } from './pubgitem.model';
import { PubgitemPopupService } from './pubgitem-popup.service';
import { PubgitemService } from './pubgitem.service';

@Component({
    selector: 'jhi-pubgitem-delete-dialog',
    templateUrl: './pubgitem-delete-dialog.component.html'
})
export class PubgitemDeleteDialogComponent {

    pubgitem: Pubgitem;

    constructor(
        private pubgitemService: PubgitemService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pubgitemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'pubgitemListModification',
                content: 'Deleted an pubgitem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pubgitem-delete-popup',
    template: ''
})
export class PubgitemDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private pubgitemPopupService: PubgitemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.pubgitemPopupService
                .open(PubgitemDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
