import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';
import { RelatorioMediaIdadeTipoSangueService } from './relatorio-media-idade-tipo-sangue.service';

@Component({
  templateUrl: './relatorio-media-idade-tipo-sangue-delete-dialog.component.html',
})
export class RelatorioMediaIdadeTipoSangueDeleteDialogComponent {
  relatorioMediaIdadeTipoSangue?: IRelatorioMediaIdadeTipoSangue;

  constructor(
    protected relatorioMediaIdadeTipoSangueService: RelatorioMediaIdadeTipoSangueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.relatorioMediaIdadeTipoSangueService.delete(id).subscribe(() => {
      this.eventManager.broadcast('relatorioMediaIdadeTipoSangueListModification');
      this.activeModal.close();
    });
  }
}
