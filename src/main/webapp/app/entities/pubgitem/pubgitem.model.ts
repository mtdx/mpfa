import { BaseEntity } from './../../shared';

export class Pubgitem implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public uns?: boolean,
        public unsr?: string,
        public rank?: number,
        public sp?: number,
        public meanp?: number,
        public maxp?: number,
        public avgp?: number,
        public minp?: number,
        public lp?: number,
        public savgd?: number,
        public s24h?: number,
        public s7d?: number,
        public s30d?: number,
        public s90d?: number,
        public nid?: string,
        public uat?: any,
    ) {
        this.uns = false;
    }
}
