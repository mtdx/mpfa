import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Pubgitem } from './pubgitem.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PubgitemService {

    private resourceUrl = SERVER_API_URL + 'api/pubgitems';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/pubgitems';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(pubgitem: Pubgitem): Observable<Pubgitem> {
        const copy = this.convert(pubgitem);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(pubgitem: Pubgitem): Observable<Pubgitem> {
        const copy = this.convert(pubgitem);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Pubgitem> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    filter(req?: any, filter?: string): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        let params = options.params.toString();
        if (filter) {
            params  += '&' + filter;
            options.params = null;
        }
        return this.http.get(this.resourceUrl + '?' + params, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.uat = this.dateUtils
            .convertDateTimeFromServer(entity.uat);
    }

    private convert(pubgitem: Pubgitem): Pubgitem {
        const copy: Pubgitem = Object.assign({}, pubgitem);

        copy.uat = this.dateUtils.toDate(pubgitem.uat);
        return copy;
    }
}
