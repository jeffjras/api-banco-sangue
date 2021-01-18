import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRelatorioPercObesosHomens } from 'app/shared/model/relatorio-perc-obesos-homens.model';

@Component({
  selector: 'jhi-relatorio-perc-obesos-homens-detail',
  templateUrl: './relatorio-perc-obesos-homens-detail.component.html',
})
export class RelatorioPercObesosHomensDetailComponent implements OnInit {
  relatorioPercObesosHomens: IRelatorioPercObesosHomens | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relatorioPercObesosHomens }) => (this.relatorioPercObesosHomens = relatorioPercObesosHomens));
  }

  previousState(): void {
    window.history.back();
  }
}
