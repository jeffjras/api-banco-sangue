import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRelatorioQtdCandPorEstado } from 'app/shared/model/relatorio-qtd-cand-por-estado.model';

type EntityResponseType = HttpResponse<IRelatorioQtdCandPorEstado>;
type EntityArrayResponseType = HttpResponse<IRelatorioQtdCandPorEstado[]>;

@Injectable({ providedIn: 'root' })
export class RelatorioQtdCandPorEstadoService {
  public resourceUrl = SERVER_API_URL + 'api/relatorio-qtd-cand-por-estados';

  constructor(protected http: HttpClient) {}

  create(relatorioQtdCandPorEstado: IRelatorioQtdCandPorEstado): Observable<EntityResponseType> {
    return this.http.post<IRelatorioQtdCandPorEstado>(this.resourceUrl, relatorioQtdCandPorEstado, { observe: 'response' });
  }

  update(relatorioQtdCandPorEstado: IRelatorioQtdCandPorEstado): Observable<EntityResponseType> {
    return this.http.put<IRelatorioQtdCandPorEstado>(this.resourceUrl, relatorioQtdCandPorEstado, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRelatorioQtdCandPorEstado>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRelatorioQtdCandPorEstado[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
