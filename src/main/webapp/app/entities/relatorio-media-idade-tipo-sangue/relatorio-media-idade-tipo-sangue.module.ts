import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BancoSangueSharedModule } from 'app/shared/shared.module';
import { RelatorioMediaIdadeTipoSangueComponent } from './relatorio-media-idade-tipo-sangue.component';
import { RelatorioMediaIdadeTipoSangueDetailComponent } from './relatorio-media-idade-tipo-sangue-detail.component';
import { RelatorioMediaIdadeTipoSangueUpdateComponent } from './relatorio-media-idade-tipo-sangue-update.component';
import { RelatorioMediaIdadeTipoSangueDeleteDialogComponent } from './relatorio-media-idade-tipo-sangue-delete-dialog.component';
import { relatorioMediaIdadeTipoSangueRoute } from './relatorio-media-idade-tipo-sangue.route';

@NgModule({
  imports: [BancoSangueSharedModule, RouterModule.forChild(relatorioMediaIdadeTipoSangueRoute)],
  declarations: [
    RelatorioMediaIdadeTipoSangueComponent,
    RelatorioMediaIdadeTipoSangueDetailComponent,
    RelatorioMediaIdadeTipoSangueUpdateComponent,
    RelatorioMediaIdadeTipoSangueDeleteDialogComponent,
  ],
  entryComponents: [RelatorioMediaIdadeTipoSangueDeleteDialogComponent],
})
export class BancoSangueRelatorioMediaIdadeTipoSangueModule {}
