import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BancoSangueSharedModule } from 'app/shared/shared.module';
import { CandidatoComponent } from './candidato.component';
import { CandidatoDetailComponent } from './candidato-detail.component';
import { CandidatoUpdateComponent } from './candidato-update.component';
import { CandidatoDeleteDialogComponent } from './candidato-delete-dialog.component';
import { candidatoRoute } from './candidato.route';

@NgModule({
  imports: [BancoSangueSharedModule, RouterModule.forChild(candidatoRoute)],
  declarations: [CandidatoComponent, CandidatoDetailComponent, CandidatoUpdateComponent, CandidatoDeleteDialogComponent],
  entryComponents: [CandidatoDeleteDialogComponent],
})
export class BancoSangueCandidatoModule {}
