import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { CsgoItem } from './csgo-item.model';
import { CsgoItemService } from './csgo-item.service';

@Injectable()
export class CsgoItemPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private csgoItemService: CsgoItemService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.csgoItemService.find(id).subscribe((csgoItem) => {
                    csgoItem.added = this.datePipe
                        .transform(csgoItem.added, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.csgoItemModalRef(component, csgoItem);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.csgoItemModalRef(component, new CsgoItem());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    csgoItemModalRef(component: Component, csgoItem: CsgoItem): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.csgoItem = csgoItem;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
