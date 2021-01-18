import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';

type EntityResponseType = HttpResponse<IRelatorioMediaIdadeTipoSangue>;
type EntityArrayResponseType = HttpResponse<IRelatorioMediaIdadeTipoSangue[]>;

@Injectable({ providedIn: 'root' })
export class RelatorioMediaIdadeTipoSangueService {
  public resourceUrl = SERVER_API_URL + 'api/relatorio-media-idade-tipo-sangues';

  constructor(protected http: HttpClient) {}

  create(relatorioMediaIdadeTipoSangue: IRelatorioMediaIdadeTipoSangue): Observable<EntityResponseType> {
    return this.http.post<IRelatorioMediaIdadeTipoSangue>(this.resourceUrl, relatorioMediaIdadeTipoSangue, { observe: 'response' });
  }

  update(relatorioMediaIdadeTipoSangue: IRelatorioMediaIdadeTipoSangue): Observable<EntityResponseType> {
    return this.http.put<IRelatorioMediaIdadeTipoSangue>(this.resourceUrl, relatorioMediaIdadeTipoSangue, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRelatorioMediaIdadeTipoSangue>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRelatorioMediaIdadeTipoSangue[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
