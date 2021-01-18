import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRelatorioQtdCandPorEstado, RelatorioQtdCandPorEstado } from 'app/shared/model/relatorio-qtd-cand-por-estado.model';
import { RelatorioQtdCandPorEstadoService } from './relatorio-qtd-cand-por-estado.service';
import { RelatorioQtdCandPorEstadoComponent } from './relatorio-qtd-cand-por-estado.component';
import { RelatorioQtdCandPorEstadoDetailComponent } from './relatorio-qtd-cand-por-estado-detail.component';
import { RelatorioQtdCandPorEstadoUpdateComponent } from './relatorio-qtd-cand-por-estado-update.component';

@Injectable({ providedIn: 'root' })
export class RelatorioQtdCandPorEstadoResolve implements Resolve<IRelatorioQtdCandPorEstado> {
  constructor(private service: RelatorioQtdCandPorEstadoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRelatorioQtdCandPorEstado> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((relatorioQtdCandPorEstado: HttpResponse<RelatorioQtdCandPorEstado>) => {
          if (relatorioQtdCandPorEstado.body) {
            return of(relatorioQtdCandPorEstado.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RelatorioQtdCandPorEstado());
  }
}

export const relatorioQtdCandPorEstadoRoute: Routes = [
  {
    path: '',
    component: RelatorioQtdCandPorEstadoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioQtdCandPorEstado.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RelatorioQtdCandPorEstadoDetailComponent,
    resolve: {
      relatorioQtdCandPorEstado: RelatorioQtdCandPorEstadoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioQtdCandPorEstado.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RelatorioQtdCandPorEstadoUpdateComponent,
    resolve: {
      relatorioQtdCandPorEstado: RelatorioQtdCandPorEstadoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioQtdCandPorEstado.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RelatorioQtdCandPorEstadoUpdateComponent,
    resolve: {
      relatorioQtdCandPorEstado: RelatorioQtdCandPorEstadoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioQtdCandPorEstado.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
