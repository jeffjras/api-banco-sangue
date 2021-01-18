import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRelatorioPercObesosMulheres } from 'app/shared/model/relatorio-perc-obesos-mulheres.model';
import { RelatorioPercObesosMulheresService } from './relatorio-perc-obesos-mulheres.service';

@Component({
  templateUrl: './relatorio-perc-obesos-mulheres-delete-dialog.component.html',
})
export class RelatorioPercObesosMulheresDeleteDialogComponent {
  relatorioPercObesosMulheres?: IRelatorioPercObesosMulheres;

  constructor(
    protected relatorioPercObesosMulheresService: RelatorioPercObesosMulheresService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.relatorioPercObesosMulheresService.delete(id).subscribe(() => {
      this.eventManager.broadcast('relatorioPercObesosMulheresListModification');
      this.activeModal.close();
    });
  }
}
