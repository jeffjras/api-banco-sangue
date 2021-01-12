import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';
import { RecepcaoTipoSanguineoService } from './recepcao-tipo-sanguineo.service';

@Component({
  templateUrl: './recepcao-tipo-sanguineo-delete-dialog.component.html',
})
export class RecepcaoTipoSanguineoDeleteDialogComponent {
  recepcaoTipoSanguineo?: IRecepcaoTipoSanguineo;

  constructor(
    protected recepcaoTipoSanguineoService: RecepcaoTipoSanguineoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.recepcaoTipoSanguineoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('recepcaoTipoSanguineoListModification');
      this.activeModal.close();
    });
  }
}
