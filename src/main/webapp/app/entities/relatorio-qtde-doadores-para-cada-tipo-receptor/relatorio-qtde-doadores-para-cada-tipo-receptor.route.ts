import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import {
  IRelatorioQtdeDoadoresParaCadaTipoReceptor,
  RelatorioQtdeDoadoresParaCadaTipoReceptor,
} from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorService } from './relatorio-qtde-doadores-para-cada-tipo-receptor.service';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorComponent } from './relatorio-qtde-doadores-para-cada-tipo-receptor.component';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent } from './relatorio-qtde-doadores-para-cada-tipo-receptor-detail.component';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent } from './relatorio-qtde-doadores-para-cada-tipo-receptor-update.component';

@Injectable({ providedIn: 'root' })
export class RelatorioQtdeDoadoresParaCadaTipoReceptorResolve implements Resolve<IRelatorioQtdeDoadoresParaCadaTipoReceptor> {
  constructor(private service: RelatorioQtdeDoadoresParaCadaTipoReceptorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRelatorioQtdeDoadoresParaCadaTipoReceptor> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((relatorioQtdeDoadoresParaCadaTipoReceptor: HttpResponse<RelatorioQtdeDoadoresParaCadaTipoReceptor>) => {
          if (relatorioQtdeDoadoresParaCadaTipoReceptor.body) {
            return of(relatorioQtdeDoadoresParaCadaTipoReceptor.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RelatorioQtdeDoadoresParaCadaTipoReceptor());
  }
}

export const relatorioQtdeDoadoresParaCadaTipoReceptorRoute: Routes = [
  {
    path: '',
    component: RelatorioQtdeDoadoresParaCadaTipoReceptorComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioQtdeDoadoresParaCadaTipoReceptor.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent,
    resolve: {
      relatorioQtdeDoadoresParaCadaTipoReceptor: RelatorioQtdeDoadoresParaCadaTipoReceptorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioQtdeDoadoresParaCadaTipoReceptor.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent,
    resolve: {
      relatorioQtdeDoadoresParaCadaTipoReceptor: RelatorioQtdeDoadoresParaCadaTipoReceptorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioQtdeDoadoresParaCadaTipoReceptor.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent,
    resolve: {
      relatorioQtdeDoadoresParaCadaTipoReceptor: RelatorioQtdeDoadoresParaCadaTipoReceptorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioQtdeDoadoresParaCadaTipoReceptor.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
