import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRelatorioPercObesosHomens, RelatorioPercObesosHomens } from 'app/shared/model/relatorio-perc-obesos-homens.model';
import { RelatorioPercObesosHomensService } from './relatorio-perc-obesos-homens.service';
import { RelatorioPercObesosHomensComponent } from './relatorio-perc-obesos-homens.component';
import { RelatorioPercObesosHomensDetailComponent } from './relatorio-perc-obesos-homens-detail.component';
import { RelatorioPercObesosHomensUpdateComponent } from './relatorio-perc-obesos-homens-update.component';

@Injectable({ providedIn: 'root' })
export class RelatorioPercObesosHomensResolve implements Resolve<IRelatorioPercObesosHomens> {
  constructor(private service: RelatorioPercObesosHomensService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRelatorioPercObesosHomens> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((relatorioPercObesosHomens: HttpResponse<RelatorioPercObesosHomens>) => {
          if (relatorioPercObesosHomens.body) {
            return of(relatorioPercObesosHomens.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RelatorioPercObesosHomens());
  }
}

export const relatorioPercObesosHomensRoute: Routes = [
  {
    path: '',
    component: RelatorioPercObesosHomensComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioPercObesosHomens.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RelatorioPercObesosHomensDetailComponent,
    resolve: {
      relatorioPercObesosHomens: RelatorioPercObesosHomensResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioPercObesosHomens.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RelatorioPercObesosHomensUpdateComponent,
    resolve: {
      relatorioPercObesosHomens: RelatorioPercObesosHomensResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioPercObesosHomens.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RelatorioPercObesosHomensUpdateComponent,
    resolve: {
      relatorioPercObesosHomens: RelatorioPercObesosHomensResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioPercObesosHomens.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
