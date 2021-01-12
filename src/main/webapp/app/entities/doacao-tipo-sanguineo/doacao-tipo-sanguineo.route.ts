import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDoacaoTipoSanguineo, DoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';
import { DoacaoTipoSanguineoService } from './doacao-tipo-sanguineo.service';
import { DoacaoTipoSanguineoComponent } from './doacao-tipo-sanguineo.component';
import { DoacaoTipoSanguineoDetailComponent } from './doacao-tipo-sanguineo-detail.component';
import { DoacaoTipoSanguineoUpdateComponent } from './doacao-tipo-sanguineo-update.component';

@Injectable({ providedIn: 'root' })
export class DoacaoTipoSanguineoResolve implements Resolve<IDoacaoTipoSanguineo> {
  constructor(private service: DoacaoTipoSanguineoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDoacaoTipoSanguineo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((doacaoTipoSanguineo: HttpResponse<DoacaoTipoSanguineo>) => {
          if (doacaoTipoSanguineo.body) {
            return of(doacaoTipoSanguineo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DoacaoTipoSanguineo());
  }
}

export const doacaoTipoSanguineoRoute: Routes = [
  {
    path: '',
    component: DoacaoTipoSanguineoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.doacaoTipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DoacaoTipoSanguineoDetailComponent,
    resolve: {
      doacaoTipoSanguineo: DoacaoTipoSanguineoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.doacaoTipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DoacaoTipoSanguineoUpdateComponent,
    resolve: {
      doacaoTipoSanguineo: DoacaoTipoSanguineoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.doacaoTipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DoacaoTipoSanguineoUpdateComponent,
    resolve: {
      doacaoTipoSanguineo: DoacaoTipoSanguineoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bancoSangueApp.doacaoTipoSanguineo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
