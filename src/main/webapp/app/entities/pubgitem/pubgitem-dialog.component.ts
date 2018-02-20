import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Pubgitem } from './pubgitem.model';
import { PubgitemPopupService } from './pubgitem-popup.service';
import { PubgitemService } from './pubgitem.service';

@Component({
    selector: 'jhi-pubgitem-dialog',
    templateUrl: './pubgitem-dialog.component.html'
})
export class PubgitemDialogComponent implements OnInit {

    pubgitem: Pubgitem;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private pubgitemService: PubgitemService,
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
        if (this.pubgitem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.pubgitemService.update(this.pubgitem));
        } else {
            this.subscribeToSaveResponse(
                this.pubgitemService.create(this.pubgitem));
        }
    }

    private subscribeToSaveResponse(result: Observable<Pubgitem>) {
        result.subscribe((res: Pubgitem) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Pubgitem) {
        this.eventManager.broadcast({ name: 'pubgitemListModification', content: 'OK'});
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
    selector: 'jhi-pubgitem-popup',
    template: ''
})
export class PubgitemPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private pubgitemPopupService: PubgitemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.pubgitemPopupService
                    .open(PubgitemDialogComponent as Component, params['id']);
            } else {
                this.pubgitemPopupService
                    .open(PubgitemDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
