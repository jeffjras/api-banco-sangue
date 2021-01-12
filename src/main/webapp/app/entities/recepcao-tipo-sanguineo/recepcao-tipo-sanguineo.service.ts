import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';

type EntityResponseType = HttpResponse<IRecepcaoTipoSanguineo>;
type EntityArrayResponseType = HttpResponse<IRecepcaoTipoSanguineo[]>;

@Injectable({ providedIn: 'root' })
export class RecepcaoTipoSanguineoService {
  public resourceUrl = SERVER_API_URL + 'api/recepcao-tipo-sanguineos';

  constructor(protected http: HttpClient) {}

  create(recepcaoTipoSanguineo: IRecepcaoTipoSanguineo): Observable<EntityResponseType> {
    return this.http.post<IRecepcaoTipoSanguineo>(this.resourceUrl, recepcaoTipoSanguineo, { observe: 'response' });
  }

  update(recepcaoTipoSanguineo: IRecepcaoTipoSanguineo): Observable<EntityResponseType> {
    return this.http.put<IRecepcaoTipoSanguineo>(this.resourceUrl, recepcaoTipoSanguineo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRecepcaoTipoSanguineo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRecepcaoTipoSanguineo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
