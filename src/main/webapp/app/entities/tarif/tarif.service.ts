import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITarif } from 'app/shared/model/tarif.model';

type EntityResponseType = HttpResponse<ITarif>;
type EntityArrayResponseType = HttpResponse<ITarif[]>;

@Injectable({ providedIn: 'root' })
export class TarifService {
    public resourceUrl = SERVER_API_URL + 'api/tarifs';

    constructor(protected http: HttpClient) {}

    create(tarif: ITarif): Observable<EntityResponseType> {
        return this.http.post<ITarif>(this.resourceUrl, tarif, { observe: 'response' });
    }

    update(tarif: ITarif): Observable<EntityResponseType> {
        return this.http.put<ITarif>(this.resourceUrl, tarif, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITarif>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITarif[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
