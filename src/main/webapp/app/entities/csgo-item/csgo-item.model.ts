import {BaseEntity} from './../../shared';

export class CsgoItem implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public sp?: number,
        public opm?: boolean,
        public vol?: number,
        public mp7?: number,
        public avg7?: number,
        public lp7?: number,
        public hp7?: number,
        public mad7?: number,
        public dp7?: number,
        public trend7?: number,
        public vol7?: number,
        public mp30?: number,
        public avg30?: number,
        public lp30?: number,
        public hp30?: number,
        public mad30?: number,
        public dp30?: number,
        public trend30?: number,
        public vol30?: number,
        public mpAll?: number,
        public avgAll?: number,
        public lpAll?: number,
        public hpAll?: number,
        public madAll?: number,
        public dpAll?: number,
        public trendAll?: number,
        public volAll?: number,
        public cfp?: number,
        public iop?: number,
        public dcx?: number,
        public added?: any,
    ) {
        this.opm = false;
    }
}
