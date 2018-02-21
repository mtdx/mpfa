import { BaseEntity } from './../../shared';

export class Pubgitem implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public uns?: boolean,
        public unsr?: string,
        public rank?: number,
        public sp?: number,
        public maxp?: number,
        public avgp?: number,
        public minp?: number,
        public savgd?: number,
        public s24h?: number,
        public s7d?: number,
        public s30d?: number,
        public s90d?: number,
        public cfp?: number,
        public iop?: number,
        public dcx?: number,
        public dopx?: number,
        public oplp?: number,
        public opq?: number,
        public nid?: string,
        public uat?: any,
    ) {
        this.uns = false;
    }
}
