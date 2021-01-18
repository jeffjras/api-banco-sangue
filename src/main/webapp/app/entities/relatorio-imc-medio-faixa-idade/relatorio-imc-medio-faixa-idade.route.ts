import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRelatorioImcMedioFaixaIdade, RelatorioImcMedioFaixaIdade } from 'app/shared/model/relatorio-imc-medio-faixa-idade.model';
import { RelatorioImcMedioFaixaIdadeService } from './relatorio-imc-medio-faixa-idade.service';
import { RelatorioImcMedioFaixaIdadeComponent } from './relatorio-imc-medio-faixa-idade.component';
import { RelatorioImcMedioFaixaIdadeDetailComponent } from './relatorio-imc-medio-faixa-idade-detail.component';
import { RelatorioImcMedioFaixaIdadeUpdateComponent } from './relatorio-imc-medio-faixa-idade-update.component';

@Injectable({ providedIn: 'root' })
export class RelatorioImcMedioFaixaIdadeResolve implements Resolve<IRelatorioImcMedioFaixaIdade> {
  constructor(private service: RelatorioImcMedioFaixaIdadeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRelatorioImcMedioFaixaIdade> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((relatorioImcMedioFaixaIdade: HttpResponse<RelatorioImcMedioFaixaIdade>) => {
          if (relatorioImcMedioFaixaIdade.body) {
            return of(relatorioImcMedioFaixaIdade.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RelatorioImcMedioFaixaIdade());
  }
}

export const relatorioImcMedioFaixaIdadeRoute: Routes = [
  {
    path: '',
    component: RelatorioImcMedioFaixaIdadeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioImcMedioFaixaIdade.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RelatorioImcMedioFaixaIdadeDetailComponent,
    resolve: {
      relatorioImcMedioFaixaIdade: RelatorioImcMedioFaixaIdadeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioImcMedioFaixaIdade.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RelatorioImcMedioFaixaIdadeUpdateComponent,
    resolve: {
      relatorioImcMedioFaixaIdade: RelatorioImcMedioFaixaIdadeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioImcMedioFaixaIdade.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RelatorioImcMedioFaixaIdadeUpdateComponent,
    resolve: {
      relatorioImcMedioFaixaIdade: RelatorioImcMedioFaixaIdadeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioImcMedioFaixaIdade.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
