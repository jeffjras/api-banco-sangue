import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRelatorioImcMedioFaixaIdade } from 'app/shared/model/relatorio-imc-medio-faixa-idade.model';
import { RelatorioImcMedioFaixaIdadeService } from './relatorio-imc-medio-faixa-idade.service';

@Component({
  templateUrl: './relatorio-imc-medio-faixa-idade-delete-dialog.component.html',
})
export class RelatorioImcMedioFaixaIdadeDeleteDialogComponent {
  relatorioImcMedioFaixaIdade?: IRelatorioImcMedioFaixaIdade;

  constructor(
    protected relatorioImcMedioFaixaIdadeService: RelatorioImcMedioFaixaIdadeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.relatorioImcMedioFaixaIdadeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('relatorioImcMedioFaixaIdadeListModification');
      this.activeModal.close();
    });
  }
}
