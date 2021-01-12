import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';

type EntityResponseType = HttpResponse<IDoacaoTipoSanguineo>;
type EntityArrayResponseType = HttpResponse<IDoacaoTipoSanguineo[]>;

@Injectable({ providedIn: 'root' })
export class DoacaoTipoSanguineoService {
  public resourceUrl = SERVER_API_URL + 'api/doacao-tipo-sanguineos';

  constructor(protected http: HttpClient) {}

  create(doacaoTipoSanguineo: IDoacaoTipoSanguineo): Observable<EntityResponseType> {
    return this.http.post<IDoacaoTipoSanguineo>(this.resourceUrl, doacaoTipoSanguineo, { observe: 'response' });
  }

  update(doacaoTipoSanguineo: IDoacaoTipoSanguineo): Observable<EntityResponseType> {
    return this.http.put<IDoacaoTipoSanguineo>(this.resourceUrl, doacaoTipoSanguineo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDoacaoTipoSanguineo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDoacaoTipoSanguineo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
