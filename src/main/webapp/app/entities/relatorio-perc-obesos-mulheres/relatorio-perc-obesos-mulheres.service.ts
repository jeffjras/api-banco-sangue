import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRelatorioPercObesosMulheres } from 'app/shared/model/relatorio-perc-obesos-mulheres.model';

type EntityResponseType = HttpResponse<IRelatorioPercObesosMulheres>;
type EntityArrayResponseType = HttpResponse<IRelatorioPercObesosMulheres[]>;

@Injectable({ providedIn: 'root' })
export class RelatorioPercObesosMulheresService {
  public resourceUrl = SERVER_API_URL + 'api/relatorio-perc-obesos-mulheres';

  constructor(protected http: HttpClient) {}

  create(relatorioPercObesosMulheres: IRelatorioPercObesosMulheres): Observable<EntityResponseType> {
    return this.http.post<IRelatorioPercObesosMulheres>(this.resourceUrl, relatorioPercObesosMulheres, { observe: 'response' });
  }

  update(relatorioPercObesosMulheres: IRelatorioPercObesosMulheres): Observable<EntityResponseType> {
    return this.http.put<IRelatorioPercObesosMulheres>(this.resourceUrl, relatorioPercObesosMulheres, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRelatorioPercObesosMulheres>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRelatorioPercObesosMulheres[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
