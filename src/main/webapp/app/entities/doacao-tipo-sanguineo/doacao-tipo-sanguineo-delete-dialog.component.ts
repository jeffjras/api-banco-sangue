import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';
import { DoacaoTipoSanguineoService } from './doacao-tipo-sanguineo.service';

@Component({
  templateUrl: './doacao-tipo-sanguineo-delete-dialog.component.html',
})
export class DoacaoTipoSanguineoDeleteDialogComponent {
  doacaoTipoSanguineo?: IDoacaoTipoSanguineo;

  constructor(
    protected doacaoTipoSanguineoService: DoacaoTipoSanguineoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.doacaoTipoSanguineoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('doacaoTipoSanguineoListModification');
      this.activeModal.close();
    });
  }
}
