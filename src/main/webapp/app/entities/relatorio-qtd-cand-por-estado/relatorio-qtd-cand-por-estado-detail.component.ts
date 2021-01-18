import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRelatorioQtdCandPorEstado } from 'app/shared/model/relatorio-qtd-cand-por-estado.model';

@Component({
  selector: 'jhi-relatorio-qtd-cand-por-estado-detail',
  templateUrl: './relatorio-qtd-cand-por-estado-detail.component.html',
})
export class RelatorioQtdCandPorEstadoDetailComponent implements OnInit {
  relatorioQtdCandPorEstado: IRelatorioQtdCandPorEstado | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relatorioQtdCandPorEstado }) => (this.relatorioQtdCandPorEstado = relatorioQtdCandPorEstado));
  }

  previousState(): void {
    window.history.back();
  }
}
