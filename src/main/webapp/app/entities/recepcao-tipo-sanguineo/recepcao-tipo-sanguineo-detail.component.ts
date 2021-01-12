import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';

@Component({
  selector: 'jhi-recepcao-tipo-sanguineo-detail',
  templateUrl: './recepcao-tipo-sanguineo-detail.component.html',
})
export class RecepcaoTipoSanguineoDetailComponent implements OnInit {
  recepcaoTipoSanguineo: IRecepcaoTipoSanguineo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ recepcaoTipoSanguineo }) => (this.recepcaoTipoSanguineo = recepcaoTipoSanguineo));
  }

  previousState(): void {
    window.history.back();
  }
}
