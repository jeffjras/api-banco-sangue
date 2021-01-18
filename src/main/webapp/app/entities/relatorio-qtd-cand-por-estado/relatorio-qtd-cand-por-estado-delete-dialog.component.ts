import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRelatorioQtdCandPorEstado } from 'app/shared/model/relatorio-qtd-cand-por-estado.model';
import { RelatorioQtdCandPorEstadoService } from './relatorio-qtd-cand-por-estado.service';

@Component({
  templateUrl: './relatorio-qtd-cand-por-estado-delete-dialog.component.html',
})
export class RelatorioQtdCandPorEstadoDeleteDialogComponent {
  relatorioQtdCandPorEstado?: IRelatorioQtdCandPorEstado;

  constructor(
    protected relatorioQtdCandPorEstadoService: RelatorioQtdCandPorEstadoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.relatorioQtdCandPorEstadoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('relatorioQtdCandPorEstadoListModification');
      this.activeModal.close();
    });
  }
}
