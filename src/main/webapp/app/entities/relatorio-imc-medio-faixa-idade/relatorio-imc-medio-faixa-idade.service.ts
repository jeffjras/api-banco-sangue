import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRelatorioImcMedioFaixaIdade } from 'app/shared/model/relatorio-imc-medio-faixa-idade.model';

type EntityResponseType = HttpResponse<IRelatorioImcMedioFaixaIdade>;
type EntityArrayResponseType = HttpResponse<IRelatorioImcMedioFaixaIdade[]>;

@Injectable({ providedIn: 'root' })
export class RelatorioImcMedioFaixaIdadeService {
  public resourceUrl = SERVER_API_URL + 'api/relatorio-imc-medio-faixa-idades';

  constructor(protected http: HttpClient) {}

  create(relatorioImcMedioFaixaIdade: IRelatorioImcMedioFaixaIdade): Observable<EntityResponseType> {
    return this.http.post<IRelatorioImcMedioFaixaIdade>(this.resourceUrl, relatorioImcMedioFaixaIdade, { observe: 'response' });
  }

  update(relatorioImcMedioFaixaIdade: IRelatorioImcMedioFaixaIdade): Observable<EntityResponseType> {
    return this.http.put<IRelatorioImcMedioFaixaIdade>(this.resourceUrl, relatorioImcMedioFaixaIdade, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRelatorioImcMedioFaixaIdade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRelatorioImcMedioFaixaIdade[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
