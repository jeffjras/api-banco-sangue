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
      {
        path: 'relatorio-qtd-cand-por-estado',
        loadChildren: () =>
          import('./relatorio-qtd-cand-por-estado/relatorio-qtd-cand-por-estado.module').then(
            m => m.BancoSangueRelatorioQtdCandPorEstadoModule
          ),
      },
      {
        path: 'relatorio-imc-medio-faixa-idade',
        loadChildren: () =>
          import('./relatorio-imc-medio-faixa-idade/relatorio-imc-medio-faixa-idade.module').then(
            m => m.BancoSangueRelatorioImcMedioFaixaIdadeModule
          ),
      },
      {
        path: 'relatorio-perc-obesos-homens',
        loadChildren: () =>
          import('./relatorio-perc-obesos-homens/relatorio-perc-obesos-homens.module').then(
            m => m.BancoSangueRelatorioPercObesosHomensModule
          ),
      },
      {
        path: 'relatorio-perc-obesos-mulheres',
        loadChildren: () =>
          import('./relatorio-perc-obesos-mulheres/relatorio-perc-obesos-mulheres.module').then(
            m => m.BancoSangueRelatorioPercObesosMulheresModule
          ),
      },
      {
        path: 'relatorio-media-idade-tipo-sangue',
        loadChildren: () =>
          import('./relatorio-media-idade-tipo-sangue/relatorio-media-idade-tipo-sangue.module').then(
            m => m.BancoSangueRelatorioMediaIdadeTipoSangueModule
          ),
      },
      {
        path: 'relatorio-qtde-doadores-para-cada-tipo-receptor',
        loadChildren: () =>
          import('./relatorio-qtde-doadores-para-cada-tipo-receptor/relatorio-qtde-doadores-para-cada-tipo-receptor.module').then(
            m => m.BancoSangueRelatorioQtdeDoadoresParaCadaTipoReceptorModule
          ),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BancoSangueEntityModule {}
