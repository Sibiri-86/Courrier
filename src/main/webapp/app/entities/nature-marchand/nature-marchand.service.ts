import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INatureMarchand } from 'app/shared/model/nature-marchand.model';

type EntityResponseType = HttpResponse<INatureMarchand>;
type EntityArrayResponseType = HttpResponse<INatureMarchand[]>;

@Injectable({ providedIn: 'root' })
export class NatureMarchandService {
    public resourceUrl = SERVER_API_URL + 'api/nature-marchands';

    constructor(protected http: HttpClient) {}

    create(natureMarchand: INatureMarchand): Observable<EntityResponseType> {
        return this.http.post<INatureMarchand>(this.resourceUrl, natureMarchand, { observe: 'response' });
    }

    update(natureMarchand: INatureMarchand): Observable<EntityResponseType> {
        return this.http.put<INatureMarchand>(this.resourceUrl, natureMarchand, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INatureMarchand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INatureMarchand[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
