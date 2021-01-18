import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';

@Component({
  selector: 'jhi-relatorio-media-idade-tipo-sangue-detail',
  templateUrl: './relatorio-media-idade-tipo-sangue-detail.component.html',
})
export class RelatorioMediaIdadeTipoSangueDetailComponent implements OnInit {
  relatorioMediaIdadeTipoSangue: IRelatorioMediaIdadeTipoSangue | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ relatorioMediaIdadeTipoSangue }) => (this.relatorioMediaIdadeTipoSangue = relatorioMediaIdadeTipoSangue)
    );
  }

  previousState(): void {
    window.history.back();
  }
}
