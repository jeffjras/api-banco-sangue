import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRelatorioPercObesosHomens } from 'app/shared/model/relatorio-perc-obesos-homens.model';

type EntityResponseType = HttpResponse<IRelatorioPercObesosHomens>;
type EntityArrayResponseType = HttpResponse<IRelatorioPercObesosHomens[]>;

@Injectable({ providedIn: 'root' })
export class RelatorioPercObesosHomensService {
  public resourceUrl = SERVER_API_URL + 'api/relatorio-perc-obesos-homens';

  constructor(protected http: HttpClient) {}

  create(relatorioPercObesosHomens: IRelatorioPercObesosHomens): Observable<EntityResponseType> {
    return this.http.post<IRelatorioPercObesosHomens>(this.resourceUrl, relatorioPercObesosHomens, { observe: 'response' });
  }

  update(relatorioPercObesosHomens: IRelatorioPercObesosHomens): Observable<EntityResponseType> {
    return this.http.put<IRelatorioPercObesosHomens>(this.resourceUrl, relatorioPercObesosHomens, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRelatorioPercObesosHomens>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRelatorioPercObesosHomens[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
