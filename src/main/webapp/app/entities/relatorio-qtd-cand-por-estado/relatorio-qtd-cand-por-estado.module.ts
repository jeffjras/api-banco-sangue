import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BancoSangueSharedModule } from 'app/shared/shared.module';
import { RelatorioQtdCandPorEstadoComponent } from './relatorio-qtd-cand-por-estado.component';
import { RelatorioQtdCandPorEstadoDetailComponent } from './relatorio-qtd-cand-por-estado-detail.component';
import { RelatorioQtdCandPorEstadoUpdateComponent } from './relatorio-qtd-cand-por-estado-update.component';
import { RelatorioQtdCandPorEstadoDeleteDialogComponent } from './relatorio-qtd-cand-por-estado-delete-dialog.component';
import { relatorioQtdCandPorEstadoRoute } from './relatorio-qtd-cand-por-estado.route';

@NgModule({
  imports: [BancoSangueSharedModule, RouterModule.forChild(relatorioQtdCandPorEstadoRoute)],
  declarations: [
    RelatorioQtdCandPorEstadoComponent,
    RelatorioQtdCandPorEstadoDetailComponent,
    RelatorioQtdCandPorEstadoUpdateComponent,
    RelatorioQtdCandPorEstadoDeleteDialogComponent,
  ],
  entryComponents: [RelatorioQtdCandPorEstadoDeleteDialogComponent],
})
export class BancoSangueRelatorioQtdCandPorEstadoModule {}
