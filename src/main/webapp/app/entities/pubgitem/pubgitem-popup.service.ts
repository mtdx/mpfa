import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Pubgitem } from './pubgitem.model';
import { PubgitemService } from './pubgitem.service';

@Injectable()
export class PubgitemPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private pubgitemService: PubgitemService

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
                this.pubgitemService.find(id).subscribe((pubgitem) => {
                    pubgitem.uat = this.datePipe
                        .transform(pubgitem.uat, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.pubgitemModalRef(component, pubgitem);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.pubgitemModalRef(component, new Pubgitem());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    pubgitemModalRef(component: Component, pubgitem: Pubgitem): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.pubgitem = pubgitem;
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
