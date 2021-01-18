import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BancoSangueSharedModule } from 'app/shared/shared.module';
import { RelatorioImcMedioFaixaIdadeComponent } from './relatorio-imc-medio-faixa-idade.component';
import { RelatorioImcMedioFaixaIdadeDetailComponent } from './relatorio-imc-medio-faixa-idade-detail.component';
import { RelatorioImcMedioFaixaIdadeUpdateComponent } from './relatorio-imc-medio-faixa-idade-update.component';
import { RelatorioImcMedioFaixaIdadeDeleteDialogComponent } from './relatorio-imc-medio-faixa-idade-delete-dialog.component';
import { relatorioImcMedioFaixaIdadeRoute } from './relatorio-imc-medio-faixa-idade.route';

@NgModule({
  imports: [BancoSangueSharedModule, RouterModule.forChild(relatorioImcMedioFaixaIdadeRoute)],
  declarations: [
    RelatorioImcMedioFaixaIdadeComponent,
    RelatorioImcMedioFaixaIdadeDetailComponent,
    RelatorioImcMedioFaixaIdadeUpdateComponent,
    RelatorioImcMedioFaixaIdadeDeleteDialogComponent,
  ],
  entryComponents: [RelatorioImcMedioFaixaIdadeDeleteDialogComponent],
})
export class BancoSangueRelatorioImcMedioFaixaIdadeModule {}
