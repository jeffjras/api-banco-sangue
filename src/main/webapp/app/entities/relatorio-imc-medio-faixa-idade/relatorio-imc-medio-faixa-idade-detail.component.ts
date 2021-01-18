import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRelatorioImcMedioFaixaIdade } from 'app/shared/model/relatorio-imc-medio-faixa-idade.model';

@Component({
  selector: 'jhi-relatorio-imc-medio-faixa-idade-detail',
  templateUrl: './relatorio-imc-medio-faixa-idade-detail.component.html',
})
export class RelatorioImcMedioFaixaIdadeDetailComponent implements OnInit {
  relatorioImcMedioFaixaIdade: IRelatorioImcMedioFaixaIdade | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ relatorioImcMedioFaixaIdade }) => (this.relatorioImcMedioFaixaIdade = relatorioImcMedioFaixaIdade)
    );
  }

  previousState(): void {
    window.history.back();
  }
}
