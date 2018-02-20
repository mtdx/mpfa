import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MmaCsgoItemModule } from './csgo-item/csgo-item.module';
import { MmaPubgitemModule } from './pubgitem/pubgitem.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        MmaCsgoItemModule,
        MmaPubgitemModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MmaEntityModule {}
