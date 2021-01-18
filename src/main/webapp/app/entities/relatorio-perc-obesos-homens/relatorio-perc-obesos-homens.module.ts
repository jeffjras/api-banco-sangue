import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BancoSangueSharedModule } from 'app/shared/shared.module';
import { RelatorioPercObesosHomensComponent } from './relatorio-perc-obesos-homens.component';
import { RelatorioPercObesosHomensDetailComponent } from './relatorio-perc-obesos-homens-detail.component';
import { RelatorioPercObesosHomensUpdateComponent } from './relatorio-perc-obesos-homens-update.component';
import { RelatorioPercObesosHomensDeleteDialogComponent } from './relatorio-perc-obesos-homens-delete-dialog.component';
import { relatorioPercObesosHomensRoute } from './relatorio-perc-obesos-homens.route';

@NgModule({
  imports: [BancoSangueSharedModule, RouterModule.forChild(relatorioPercObesosHomensRoute)],
  declarations: [
    RelatorioPercObesosHomensComponent,
    RelatorioPercObesosHomensDetailComponent,
    RelatorioPercObesosHomensUpdateComponent,
    RelatorioPercObesosHomensDeleteDialogComponent,
  ],
  entryComponents: [RelatorioPercObesosHomensDeleteDialogComponent],
})
export class BancoSangueRelatorioPercObesosHomensModule {}
