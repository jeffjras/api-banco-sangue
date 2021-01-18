import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BancoSangueSharedModule } from 'app/shared/shared.module';
import { RelatorioPercObesosMulheresComponent } from './relatorio-perc-obesos-mulheres.component';
import { RelatorioPercObesosMulheresDetailComponent } from './relatorio-perc-obesos-mulheres-detail.component';
import { RelatorioPercObesosMulheresUpdateComponent } from './relatorio-perc-obesos-mulheres-update.component';
import { RelatorioPercObesosMulheresDeleteDialogComponent } from './relatorio-perc-obesos-mulheres-delete-dialog.component';
import { relatorioPercObesosMulheresRoute } from './relatorio-perc-obesos-mulheres.route';

@NgModule({
  imports: [BancoSangueSharedModule, RouterModule.forChild(relatorioPercObesosMulheresRoute)],
  declarations: [
    RelatorioPercObesosMulheresComponent,
    RelatorioPercObesosMulheresDetailComponent,
    RelatorioPercObesosMulheresUpdateComponent,
    RelatorioPercObesosMulheresDeleteDialogComponent,
  ],
  entryComponents: [RelatorioPercObesosMulheresDeleteDialogComponent],
})
export class BancoSangueRelatorioPercObesosMulheresModule {}
