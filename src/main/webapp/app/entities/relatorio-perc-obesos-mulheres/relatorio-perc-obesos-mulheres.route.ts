import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRelatorioPercObesosMulheres, RelatorioPercObesosMulheres } from 'app/shared/model/relatorio-perc-obesos-mulheres.model';
import { RelatorioPercObesosMulheresService } from './relatorio-perc-obesos-mulheres.service';
import { RelatorioPercObesosMulheresComponent } from './relatorio-perc-obesos-mulheres.component';
import { RelatorioPercObesosMulheresDetailComponent } from './relatorio-perc-obesos-mulheres-detail.component';
import { RelatorioPercObesosMulheresUpdateComponent } from './relatorio-perc-obesos-mulheres-update.component';

@Injectable({ providedIn: 'root' })
export class RelatorioPercObesosMulheresResolve implements Resolve<IRelatorioPercObesosMulheres> {
  constructor(private service: RelatorioPercObesosMulheresService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRelatorioPercObesosMulheres> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((relatorioPercObesosMulheres: HttpResponse<RelatorioPercObesosMulheres>) => {
          if (relatorioPercObesosMulheres.body) {
            return of(relatorioPercObesosMulheres.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RelatorioPercObesosMulheres());
  }
}

export const relatorioPercObesosMulheresRoute: Routes = [
  {
    path: '',
    component: RelatorioPercObesosMulheresComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioPercObesosMulheres.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RelatorioPercObesosMulheresDetailComponent,
    resolve: {
      relatorioPercObesosMulheres: RelatorioPercObesosMulheresResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioPercObesosMulheres.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RelatorioPercObesosMulheresUpdateComponent,
    resolve: {
      relatorioPercObesosMulheres: RelatorioPercObesosMulheresResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioPercObesosMulheres.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RelatorioPercObesosMulheresUpdateComponent,
    resolve: {
      relatorioPercObesosMulheres: RelatorioPercObesosMulheresResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioPercObesosMulheres.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
