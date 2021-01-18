import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRelatorioPercObesosMulheres } from 'app/shared/model/relatorio-perc-obesos-mulheres.model';

@Component({
  selector: 'jhi-relatorio-perc-obesos-mulheres-detail',
  templateUrl: './relatorio-perc-obesos-mulheres-detail.component.html',
})
export class RelatorioPercObesosMulheresDetailComponent implements OnInit {
  relatorioPercObesosMulheres: IRelatorioPercObesosMulheres | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ relatorioPercObesosMulheres }) => (this.relatorioPercObesosMulheres = relatorioPercObesosMulheres)
    );
  }

  previousState(): void {
    window.history.back();
  }
}
