import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRelatorioQtdCandPorEstado } from 'app/shared/model/relatorio-qtd-cand-por-estado.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RelatorioQtdCandPorEstadoService } from './relatorio-qtd-cand-por-estado.service';
import { RelatorioQtdCandPorEstadoDeleteDialogComponent } from './relatorio-qtd-cand-por-estado-delete-dialog.component';

@Component({
  selector: 'jhi-relatorio-qtd-cand-por-estado',
  templateUrl: './relatorio-qtd-cand-por-estado.component.html',
})
export class RelatorioQtdCandPorEstadoComponent implements OnInit, OnDestroy {
  relatorioQtdCandPorEstados: IRelatorioQtdCandPorEstado[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected relatorioQtdCandPorEstadoService: RelatorioQtdCandPorEstadoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.relatorioQtdCandPorEstados = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.relatorioQtdCandPorEstadoService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IRelatorioQtdCandPorEstado[]>) => this.paginateRelatorioQtdCandPorEstados(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.relatorioQtdCandPorEstados = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRelatorioQtdCandPorEstados();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRelatorioQtdCandPorEstado): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRelatorioQtdCandPorEstados(): void {
    this.eventSubscriber = this.eventManager.subscribe('relatorioQtdCandPorEstadoListModification', () => this.reset());
  }

  delete(relatorioQtdCandPorEstado: IRelatorioQtdCandPorEstado): void {
    const modalRef = this.modalService.open(RelatorioQtdCandPorEstadoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.relatorioQtdCandPorEstado = relatorioQtdCandPorEstado;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRelatorioQtdCandPorEstados(data: IRelatorioQtdCandPorEstado[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.relatorioQtdCandPorEstados.push(data[i]);
      }
    }
  }
}
