import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITailleBalle } from 'app/shared/model/taille-balle.model';

type EntityResponseType = HttpResponse<ITailleBalle>;
type EntityArrayResponseType = HttpResponse<ITailleBalle[]>;

@Injectable({ providedIn: 'root' })
export class TailleBalleService {
    public resourceUrl = SERVER_API_URL + 'api/taille-balles';

    constructor(protected http: HttpClient) {}

    create(tailleBalle: ITailleBalle): Observable<EntityResponseType> {
        return this.http.post<ITailleBalle>(this.resourceUrl, tailleBalle, { observe: 'response' });
    }

    update(tailleBalle: ITailleBalle): Observable<EntityResponseType> {
        return this.http.put<ITailleBalle>(this.resourceUrl, tailleBalle, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITailleBalle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITailleBalle[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
