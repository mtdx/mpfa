import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CsgoItem } from './csgo-item.model';
import { CsgoItemPopupService } from './csgo-item-popup.service';
import { CsgoItemService } from './csgo-item.service';

@Component({
    selector: 'jhi-csgo-item-delete-dialog',
    templateUrl: './csgo-item-delete-dialog.component.html'
})
export class CsgoItemDeleteDialogComponent {

    csgoItem: CsgoItem;

    constructor(
        private csgoItemService: CsgoItemService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.csgoItemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'csgoItemListModification',
                content: 'Deleted an csgoItem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-csgo-item-delete-popup',
    template: ''
})
export class CsgoItemDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private csgoItemPopupService: CsgoItemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.csgoItemPopupService
                .open(CsgoItemDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
