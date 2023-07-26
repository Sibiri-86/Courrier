import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeTransporteur } from 'app/shared/model/type-transporteur.model';

type EntityResponseType = HttpResponse<ITypeTransporteur>;
type EntityArrayResponseType = HttpResponse<ITypeTransporteur[]>;

@Injectable({ providedIn: 'root' })
export class TypeTransporteurService {
    public resourceUrl = SERVER_API_URL + 'api/type-transporteurs';

    constructor(protected http: HttpClient) {}

    create(typeTransporteur: ITypeTransporteur): Observable<EntityResponseType> {
        return this.http.post<ITypeTransporteur>(this.resourceUrl, typeTransporteur, { observe: 'response' });
    }

    update(typeTransporteur: ITypeTransporteur): Observable<EntityResponseType> {
        return this.http.put<ITypeTransporteur>(this.resourceUrl, typeTransporteur, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITypeTransporteur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeTransporteur[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
