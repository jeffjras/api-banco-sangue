import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRelatorioQtdeDoadoresParaCadaTipoReceptor } from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorService } from './relatorio-qtde-doadores-para-cada-tipo-receptor.service';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent } from './relatorio-qtde-doadores-para-cada-tipo-receptor-delete-dialog.component';

@Component({
  selector: 'jhi-relatorio-qtde-doadores-para-cada-tipo-receptor',
  templateUrl: './relatorio-qtde-doadores-para-cada-tipo-receptor.component.html',
})
export class RelatorioQtdeDoadoresParaCadaTipoReceptorComponent implements OnInit, OnDestroy {
  relatorioQtdeDoadoresParaCadaTipoReceptors: IRelatorioQtdeDoadoresParaCadaTipoReceptor[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected relatorioQtdeDoadoresParaCadaTipoReceptorService: RelatorioQtdeDoadoresParaCadaTipoReceptorService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.relatorioQtdeDoadoresParaCadaTipoReceptors = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.relatorioQtdeDoadoresParaCadaTipoReceptorService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IRelatorioQtdeDoadoresParaCadaTipoReceptor[]>) =>
        this.paginateRelatorioQtdeDoadoresParaCadaTipoReceptors(res.body, res.headers)
      );
  }

  reset(): void {
    this.page = 0;
    this.relatorioQtdeDoadoresParaCadaTipoReceptors = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRelatorioQtdeDoadoresParaCadaTipoReceptors();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRelatorioQtdeDoadoresParaCadaTipoReceptor): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRelatorioQtdeDoadoresParaCadaTipoReceptors(): void {
    this.eventSubscriber = this.eventManager.subscribe('relatorioQtdeDoadoresParaCadaTipoReceptorListModification', () => this.reset());
  }

  delete(relatorioQtdeDoadoresParaCadaTipoReceptor: IRelatorioQtdeDoadoresParaCadaTipoReceptor): void {
    const modalRef = this.modalService.open(RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent, {
      size: 'lg',
      backdrop: 'static',
    });
    modalRef.componentInstance.relatorioQtdeDoadoresParaCadaTipoReceptor = relatorioQtdeDoadoresParaCadaTipoReceptor;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRelatorioQtdeDoadoresParaCadaTipoReceptors(
    data: IRelatorioQtdeDoadoresParaCadaTipoReceptor[] | null,
    headers: HttpHeaders
  ): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.relatorioQtdeDoadoresParaCadaTipoReceptors.push(data[i]);
      }
    }
  }
}
