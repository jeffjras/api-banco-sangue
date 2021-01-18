import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRelatorioQtdeDoadoresParaCadaTipoReceptor } from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorService } from './relatorio-qtde-doadores-para-cada-tipo-receptor.service';

@Component({
  templateUrl: './relatorio-qtde-doadores-para-cada-tipo-receptor-delete-dialog.component.html',
})
export class RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent {
  relatorioQtdeDoadoresParaCadaTipoReceptor?: IRelatorioQtdeDoadoresParaCadaTipoReceptor;

  constructor(
    protected relatorioQtdeDoadoresParaCadaTipoReceptorService: RelatorioQtdeDoadoresParaCadaTipoReceptorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.relatorioQtdeDoadoresParaCadaTipoReceptorService.delete(id).subscribe(() => {
      this.eventManager.broadcast('relatorioQtdeDoadoresParaCadaTipoReceptorListModification');
      this.activeModal.close();
    });
  }
}
