import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'tipo-sanguineo',
        loadChildren: () => import('./tipo-sanguineo/tipo-sanguineo.module').then(m => m.BancoSangueTipoSanguineoModule),
      },
      {
        path: 'doacao-tipo-sanguineo',
        loadChildren: () =>
          import('./doacao-tipo-sanguineo/doacao-tipo-sanguineo.module').then(m => m.BancoSangueDoacaoTipoSanguineoModule),
      },
      {
        path: 'recepcao-tipo-sanguineo',
        loadChildren: () =>
          import('./recepcao-tipo-sanguineo/recepcao-tipo-sanguineo.module').then(m => m.BancoSangueRecepcaoTipoSanguineoModule),
      },
      {
        path: 'candidato',
        loadChildren: () => import('./candidato/candidato.module').then(m => m.BancoSangueCandidatoModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BancoSangueEntityModule {}
