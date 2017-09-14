import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MmaSharedModule } from '../../shared';
import {
    CsgoItemService,
    CsgoItemPopupService,
    CsgoItemComponent,
    CsgoItemDetailComponent,
    CsgoItemDialogComponent,
    CsgoItemPopupComponent,
    CsgoItemDeletePopupComponent,
    CsgoItemDeleteDialogComponent,
    csgoItemRoute,
    csgoItemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...csgoItemRoute,
    ...csgoItemPopupRoute,
];

@NgModule({
    imports: [
        MmaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CsgoItemComponent,
        CsgoItemDetailComponent,
        CsgoItemDialogComponent,
        CsgoItemDeleteDialogComponent,
        CsgoItemPopupComponent,
        CsgoItemDeletePopupComponent,
    ],
    entryComponents: [
        CsgoItemComponent,
        CsgoItemDialogComponent,
        CsgoItemPopupComponent,
        CsgoItemDeleteDialogComponent,
        CsgoItemDeletePopupComponent,
    ],
    providers: [
        CsgoItemService,
        CsgoItemPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MmaCsgoItemModule {}
