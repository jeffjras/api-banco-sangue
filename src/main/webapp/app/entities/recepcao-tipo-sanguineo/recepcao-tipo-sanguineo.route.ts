import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRecepcaoTipoSanguineo, RecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';
import { RecepcaoTipoSanguineoService } from './recepcao-tipo-sanguineo.service';
import { RecepcaoTipoSanguineoComponent } from './recepcao-tipo-sanguineo.component';
import { RecepcaoTipoSanguineoDetailComponent } from './recepcao-tipo-sanguineo-detail.component';
import { RecepcaoTipoSanguineoUpdateComponent } from './recepcao-tipo-sanguineo-update.component';

@Injectable({ providedIn: 'root' })
export class RecepcaoTipoSanguineoResolve implements Resolve<IRecepcaoTipoSanguineo> {
  constructor(private service: RecepcaoTipoSanguineoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRecepcaoTipoSanguineo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((recepcaoTipoSanguineo: HttpResponse<RecepcaoTipoSanguineo>) => {
          if (recepcaoTipoSanguineo.body) {
            return of(recepcaoTipoSanguineo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RecepcaoTipoSanguineo());
  }
}

export const recepcaoTipoSanguineoRoute: Routes = [
  {
    path: '',
    component: RecepcaoTipoSanguineoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.recepcaoTipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RecepcaoTipoSanguineoDetailComponent,
    resolve: {
      recepcaoTipoSanguineo: RecepcaoTipoSanguineoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.recepcaoTipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RecepcaoTipoSanguineoUpdateComponent,
    resolve: {
      recepcaoTipoSanguineo: RecepcaoTipoSanguineoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.recepcaoTipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RecepcaoTipoSanguineoUpdateComponent,
    resolve: {
      recepcaoTipoSanguineo: RecepcaoTipoSanguineoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.recepcaoTipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
