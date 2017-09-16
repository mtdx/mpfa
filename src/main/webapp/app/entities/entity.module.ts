import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MmaCsgoItemModule } from './csgo-item/csgo-item.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        MmaCsgoItemModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MmaEntityModule {}
