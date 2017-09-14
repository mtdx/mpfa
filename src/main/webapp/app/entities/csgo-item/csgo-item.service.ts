import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { CsgoItem } from './csgo-item.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CsgoItemService {

    private resourceUrl = SERVER_API_URL + 'api/csgo-items';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/csgo-items';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(csgoItem: CsgoItem): Observable<CsgoItem> {
        const copy = this.convert(csgoItem);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(csgoItem: CsgoItem): Observable<CsgoItem> {
        const copy = this.convert(csgoItem);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<CsgoItem> {
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
        entity.first_seen = this.dateUtils
            .convertDateTimeFromServer(entity.first_seen);
    }

    private convert(csgoItem: CsgoItem): CsgoItem {
        const copy: CsgoItem = Object.assign({}, csgoItem);

        copy.first_seen = this.dateUtils.toDate(csgoItem.first_seen);
        return copy;
    }
}
