import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MmaSharedModule } from '../../shared';
import {
    PubgitemService,
    PubgitemPopupService,
    PubgitemComponent,
    PubgitemDetailComponent,
    PubgitemDialogComponent,
    PubgitemPopupComponent,
    PubgitemDeletePopupComponent,
    PubgitemDeleteDialogComponent,
    pubgitemRoute,
    pubgitemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...pubgitemRoute,
    ...pubgitemPopupRoute,
];

@NgModule({
    imports: [
        MmaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PubgitemComponent,
        PubgitemDetailComponent,
        PubgitemDialogComponent,
        PubgitemDeleteDialogComponent,
        PubgitemPopupComponent,
        PubgitemDeletePopupComponent,
    ],
    entryComponents: [
        PubgitemComponent,
        PubgitemDialogComponent,
        PubgitemPopupComponent,
        PubgitemDeleteDialogComponent,
        PubgitemDeletePopupComponent,
    ],
    providers: [
        PubgitemService,
        PubgitemPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MmaPubgitemModule {}
