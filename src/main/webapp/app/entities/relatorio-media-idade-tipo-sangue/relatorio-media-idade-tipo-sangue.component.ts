import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RelatorioMediaIdadeTipoSangueService } from './relatorio-media-idade-tipo-sangue.service';
import { RelatorioMediaIdadeTipoSangueDeleteDialogComponent } from './relatorio-media-idade-tipo-sangue-delete-dialog.component';

@Component({
  selector: 'jhi-relatorio-media-idade-tipo-sangue',
  templateUrl: './relatorio-media-idade-tipo-sangue.component.html',
})
export class RelatorioMediaIdadeTipoSangueComponent implements OnInit, OnDestroy {
  relatorioMediaIdadeTipoSangues: IRelatorioMediaIdadeTipoSangue[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected relatorioMediaIdadeTipoSangueService: RelatorioMediaIdadeTipoSangueService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.relatorioMediaIdadeTipoSangues = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.relatorioMediaIdadeTipoSangueService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IRelatorioMediaIdadeTipoSangue[]>) =>
        this.paginateRelatorioMediaIdadeTipoSangues(res.body, res.headers)
      );
  }

  reset(): void {
    this.page = 0;
    this.relatorioMediaIdadeTipoSangues = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRelatorioMediaIdadeTipoSangues();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRelatorioMediaIdadeTipoSangue): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRelatorioMediaIdadeTipoSangues(): void {
    this.eventSubscriber = this.eventManager.subscribe('relatorioMediaIdadeTipoSangueListModification', () => this.reset());
  }

  delete(relatorioMediaIdadeTipoSangue: IRelatorioMediaIdadeTipoSangue): void {
    const modalRef = this.modalService.open(RelatorioMediaIdadeTipoSangueDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.relatorioMediaIdadeTipoSangue = relatorioMediaIdadeTipoSangue;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRelatorioMediaIdadeTipoSangues(data: IRelatorioMediaIdadeTipoSangue[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.relatorioMediaIdadeTipoSangues.push(data[i]);
      }
    }
  }
}
