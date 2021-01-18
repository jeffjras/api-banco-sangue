import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BancoSangueSharedModule } from 'app/shared/shared.module';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorComponent } from './relatorio-qtde-doadores-para-cada-tipo-receptor.component';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent } from './relatorio-qtde-doadores-para-cada-tipo-receptor-detail.component';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent } from './relatorio-qtde-doadores-para-cada-tipo-receptor-update.component';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent } from './relatorio-qtde-doadores-para-cada-tipo-receptor-delete-dialog.component';
import { relatorioQtdeDoadoresParaCadaTipoReceptorRoute } from './relatorio-qtde-doadores-para-cada-tipo-receptor.route';

@NgModule({
  imports: [BancoSangueSharedModule, RouterModule.forChild(relatorioQtdeDoadoresParaCadaTipoReceptorRoute)],
  declarations: [
    RelatorioQtdeDoadoresParaCadaTipoReceptorComponent,
    RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent,
    RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent,
    RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent,
  ],
  entryComponents: [RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent],
})
export class BancoSangueRelatorioQtdeDoadoresParaCadaTipoReceptorModule {}
