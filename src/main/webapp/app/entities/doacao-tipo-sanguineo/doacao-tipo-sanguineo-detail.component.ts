import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';

@Component({
  selector: 'jhi-doacao-tipo-sanguineo-detail',
  templateUrl: './doacao-tipo-sanguineo-detail.component.html',
})
export class DoacaoTipoSanguineoDetailComponent implements OnInit {
  doacaoTipoSanguineo: IDoacaoTipoSanguineo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ doacaoTipoSanguineo }) => (this.doacaoTipoSanguineo = doacaoTipoSanguineo));
  }

  previousState(): void {
    window.history.back();
  }
}
