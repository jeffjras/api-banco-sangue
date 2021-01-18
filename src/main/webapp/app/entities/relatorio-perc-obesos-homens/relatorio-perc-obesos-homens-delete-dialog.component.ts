import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRelatorioPercObesosHomens } from 'app/shared/model/relatorio-perc-obesos-homens.model';
import { RelatorioPercObesosHomensService } from './relatorio-perc-obesos-homens.service';

@Component({
  templateUrl: './relatorio-perc-obesos-homens-delete-dialog.component.html',
})
export class RelatorioPercObesosHomensDeleteDialogComponent {
  relatorioPercObesosHomens?: IRelatorioPercObesosHomens;

  constructor(
    protected relatorioPercObesosHomensService: RelatorioPercObesosHomensService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.relatorioPercObesosHomensService.delete(id).subscribe(() => {
      this.eventManager.broadcast('relatorioPercObesosHomensListModification');
      this.activeModal.close();
    });
  }
}
