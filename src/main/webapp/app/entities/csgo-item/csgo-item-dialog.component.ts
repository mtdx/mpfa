import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CsgoItem } from './csgo-item.model';
import { CsgoItemPopupService } from './csgo-item-popup.service';
import { CsgoItemService } from './csgo-item.service';

@Component({
    selector: 'jhi-csgo-item-dialog',
    templateUrl: './csgo-item-dialog.component.html'
})
export class CsgoItemDialogComponent implements OnInit {

    csgoItem: CsgoItem;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private csgoItemService: CsgoItemService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.csgoItem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.csgoItemService.update(this.csgoItem));
        } else {
            this.subscribeToSaveResponse(
                this.csgoItemService.create(this.csgoItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<CsgoItem>) {
        result.subscribe((res: CsgoItem) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CsgoItem) {
        this.eventManager.broadcast({ name: 'csgoItemListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-csgo-item-popup',
    template: ''
})
export class CsgoItemPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private csgoItemPopupService: CsgoItemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.csgoItemPopupService
                    .open(CsgoItemDialogComponent as Component, params['id']);
            } else {
                this.csgoItemPopupService
                    .open(CsgoItemDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
