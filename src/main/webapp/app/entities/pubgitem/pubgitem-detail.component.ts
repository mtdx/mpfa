import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Pubgitem } from './pubgitem.model';
import { PubgitemService } from './pubgitem.service';

@Component({
    selector: 'jhi-pubgitem-detail',
    templateUrl: './pubgitem-detail.component.html'
})
export class PubgitemDetailComponent implements OnInit, OnDestroy {

    pubgitem: Pubgitem;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private pubgitemService: PubgitemService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPubgitems();
    }

    load(id) {
        this.pubgitemService.find(id).subscribe((pubgitem) => {
            this.pubgitem = pubgitem;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPubgitems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'pubgitemListModification',
            (response) => this.load(this.pubgitem.id)
        );
    }
}
