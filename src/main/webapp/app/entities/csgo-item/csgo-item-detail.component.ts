import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CsgoItem } from './csgo-item.model';
import { CsgoItemService } from './csgo-item.service';

@Component({
    selector: 'jhi-csgo-item-detail',
    templateUrl: './csgo-item-detail.component.html'
})
export class CsgoItemDetailComponent implements OnInit, OnDestroy {

    csgoItem: CsgoItem;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private csgoItemService: CsgoItemService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCsgoItems();
    }

    load(id) {
        this.csgoItemService.find(id).subscribe((csgoItem) => {
            this.csgoItem = csgoItem;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCsgoItems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'csgoItemListModification',
            (response) => this.load(this.csgoItem.id)
        );
    }
}
