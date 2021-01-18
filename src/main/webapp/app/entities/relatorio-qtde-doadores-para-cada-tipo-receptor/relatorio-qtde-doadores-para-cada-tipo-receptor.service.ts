import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRelatorioQtdeDoadoresParaCadaTipoReceptor } from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';

type EntityResponseType = HttpResponse<IRelatorioQtdeDoadoresParaCadaTipoReceptor>;
type EntityArrayResponseType = HttpResponse<IRelatorioQtdeDoadoresParaCadaTipoReceptor[]>;

@Injectable({ providedIn: 'root' })
export class RelatorioQtdeDoadoresParaCadaTipoReceptorService {
  public resourceUrl = SERVER_API_URL + 'api/relatorio-qtde-doadores-para-cada-tipo-receptors';

  constructor(protected http: HttpClient) {}

  create(relatorioQtdeDoadoresParaCadaTipoReceptor: IRelatorioQtdeDoadoresParaCadaTipoReceptor): Observable<EntityResponseType> {
    return this.http.post<IRelatorioQtdeDoadoresParaCadaTipoReceptor>(this.resourceUrl, relatorioQtdeDoadoresParaCadaTipoReceptor, {
      observe: 'response',
    });
  }

  update(relatorioQtdeDoadoresParaCadaTipoReceptor: IRelatorioQtdeDoadoresParaCadaTipoReceptor): Observable<EntityResponseType> {
    return this.http.put<IRelatorioQtdeDoadoresParaCadaTipoReceptor>(this.resourceUrl, relatorioQtdeDoadoresParaCadaTipoReceptor, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRelatorioQtdeDoadoresParaCadaTipoReceptor>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRelatorioQtdeDoadoresParaCadaTipoReceptor[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
