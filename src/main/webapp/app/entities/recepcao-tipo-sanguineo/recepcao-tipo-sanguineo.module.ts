import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BancoSangueSharedModule } from 'app/shared/shared.module';
import { RecepcaoTipoSanguineoComponent } from './recepcao-tipo-sanguineo.component';
import { RecepcaoTipoSanguineoDetailComponent } from './recepcao-tipo-sanguineo-detail.component';
import { RecepcaoTipoSanguineoUpdateComponent } from './recepcao-tipo-sanguineo-update.component';
import { RecepcaoTipoSanguineoDeleteDialogComponent } from './recepcao-tipo-sanguineo-delete-dialog.component';
import { recepcaoTipoSanguineoRoute } from './recepcao-tipo-sanguineo.route';

@NgModule({
  imports: [BancoSangueSharedModule, RouterModule.forChild(recepcaoTipoSanguineoRoute)],
  declarations: [
    RecepcaoTipoSanguineoComponent,
    RecepcaoTipoSanguineoDetailComponent,
    RecepcaoTipoSanguineoUpdateComponent,
    RecepcaoTipoSanguineoDeleteDialogComponent,
  ],
  entryComponents: [RecepcaoTipoSanguineoDeleteDialogComponent],
})
export class BancoSangueRecepcaoTipoSanguineoModule {}
