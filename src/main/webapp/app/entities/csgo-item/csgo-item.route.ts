import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CsgoItemComponent } from './csgo-item.component';
import { CsgoItemDetailComponent } from './csgo-item-detail.component';
import { CsgoItemPopupComponent } from './csgo-item-dialog.component';
import { CsgoItemDeletePopupComponent } from './csgo-item-delete-dialog.component';

export const csgoItemRoute: Routes = [
    {
        path: 'csgo-item',
        component: CsgoItemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CsgoItems'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'csgo-item/:id',
        component: CsgoItemDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CsgoItems'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const csgoItemPopupRoute: Routes = [
    {
        path: 'csgo-item-new',
        component: CsgoItemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CsgoItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'csgo-item/:id/edit',
        component: CsgoItemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CsgoItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'csgo-item/:id/delete',
        component: CsgoItemDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CsgoItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
