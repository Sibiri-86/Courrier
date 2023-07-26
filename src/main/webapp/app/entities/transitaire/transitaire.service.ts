import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITransitaire } from 'app/shared/model/transitaire.model';

type EntityResponseType = HttpResponse<ITransitaire>;
type EntityArrayResponseType = HttpResponse<ITransitaire[]>;

@Injectable({ providedIn: 'root' })
export class TransitaireService {
    public resourceUrl = SERVER_API_URL + 'api/transitaires';

    constructor(protected http: HttpClient) {}

    create(transitaire: ITransitaire): Observable<EntityResponseType> {
        return this.http.post<ITransitaire>(this.resourceUrl, transitaire, { observe: 'response' });
    }

    update(transitaire: ITransitaire): Observable<EntityResponseType> {
        return this.http.put<ITransitaire>(this.resourceUrl, transitaire, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITransitaire>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITransitaire[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
