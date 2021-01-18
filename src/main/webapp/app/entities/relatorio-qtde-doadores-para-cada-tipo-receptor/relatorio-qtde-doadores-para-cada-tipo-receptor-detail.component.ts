import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRelatorioQtdeDoadoresParaCadaTipoReceptor } from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';

@Component({
  selector: 'jhi-relatorio-qtde-doadores-para-cada-tipo-receptor-detail',
  templateUrl: './relatorio-qtde-doadores-para-cada-tipo-receptor-detail.component.html',
})
export class RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent implements OnInit {
  relatorioQtdeDoadoresParaCadaTipoReceptor: IRelatorioQtdeDoadoresParaCadaTipoReceptor | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ relatorioQtdeDoadoresParaCadaTipoReceptor }) =>
        (this.relatorioQtdeDoadoresParaCadaTipoReceptor = relatorioQtdeDoadoresParaCadaTipoReceptor)
    );
  }

  previousState(): void {
    window.history.back();
  }
}
