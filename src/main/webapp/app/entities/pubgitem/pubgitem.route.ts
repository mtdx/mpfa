import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PubgitemComponent } from './pubgitem.component';
import { PubgitemDetailComponent } from './pubgitem-detail.component';
import { PubgitemPopupComponent } from './pubgitem-dialog.component';
import { PubgitemDeletePopupComponent } from './pubgitem-delete-dialog.component';

export const pubgitemRoute: Routes = [
    {
        path: 'pubgitem',
        component: PubgitemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pubgitems'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'pubgitem/:id',
        component: PubgitemDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pubgitems'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pubgitemPopupRoute: Routes = [
    {
        path: 'pubgitem-new',
        component: PubgitemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pubgitems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pubgitem/:id/edit',
        component: PubgitemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pubgitems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pubgitem/:id/delete',
        component: PubgitemDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Pubgitems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
