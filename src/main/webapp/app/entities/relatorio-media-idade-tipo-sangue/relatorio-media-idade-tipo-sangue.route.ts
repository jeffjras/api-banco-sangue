import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRelatorioMediaIdadeTipoSangue, RelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';
import { RelatorioMediaIdadeTipoSangueService } from './relatorio-media-idade-tipo-sangue.service';
import { RelatorioMediaIdadeTipoSangueComponent } from './relatorio-media-idade-tipo-sangue.component';
import { RelatorioMediaIdadeTipoSangueDetailComponent } from './relatorio-media-idade-tipo-sangue-detail.component';
import { RelatorioMediaIdadeTipoSangueUpdateComponent } from './relatorio-media-idade-tipo-sangue-update.component';

@Injectable({ providedIn: 'root' })
export class RelatorioMediaIdadeTipoSangueResolve implements Resolve<IRelatorioMediaIdadeTipoSangue> {
  constructor(private service: RelatorioMediaIdadeTipoSangueService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRelatorioMediaIdadeTipoSangue> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((relatorioMediaIdadeTipoSangue: HttpResponse<RelatorioMediaIdadeTipoSangue>) => {
          if (relatorioMediaIdadeTipoSangue.body) {
            return of(relatorioMediaIdadeTipoSangue.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RelatorioMediaIdadeTipoSangue());
  }
}

export const relatorioMediaIdadeTipoSangueRoute: Routes = [
  {
    path: '',
    component: RelatorioMediaIdadeTipoSangueComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioMediaIdadeTipoSangue.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RelatorioMediaIdadeTipoSangueDetailComponent,
    resolve: {
      relatorioMediaIdadeTipoSangue: RelatorioMediaIdadeTipoSangueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioMediaIdadeTipoSangue.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RelatorioMediaIdadeTipoSangueUpdateComponent,
    resolve: {
      relatorioMediaIdadeTipoSangue: RelatorioMediaIdadeTipoSangueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioMediaIdadeTipoSangue.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RelatorioMediaIdadeTipoSangueUpdateComponent,
    resolve: {
      relatorioMediaIdadeTipoSangue: RelatorioMediaIdadeTipoSangueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.relatorioMediaIdadeTipoSangue.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
