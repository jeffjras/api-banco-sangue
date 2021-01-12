import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BancoSangueSharedModule } from 'app/shared/shared.module';
import { DoacaoTipoSanguineoComponent } from './doacao-tipo-sanguineo.component';
import { DoacaoTipoSanguineoDetailComponent } from './doacao-tipo-sanguineo-detail.component';
import { DoacaoTipoSanguineoUpdateComponent } from './doacao-tipo-sanguineo-update.component';
import { DoacaoTipoSanguineoDeleteDialogComponent } from './doacao-tipo-sanguineo-delete-dialog.component';
import { doacaoTipoSanguineoRoute } from './doacao-tipo-sanguineo.route';

@NgModule({
  imports: [BancoSangueSharedModule, RouterModule.forChild(doacaoTipoSanguineoRoute)],
  declarations: [
    DoacaoTipoSanguineoComponent,
    DoacaoTipoSanguineoDetailComponent,
    DoacaoTipoSanguineoUpdateComponent,
    DoacaoTipoSanguineoDeleteDialogComponent,
  ],
  entryComponents: [DoacaoTipoSanguineoDeleteDialogComponent],
})
export class BancoSangueDoacaoTipoSanguineoModule {}
