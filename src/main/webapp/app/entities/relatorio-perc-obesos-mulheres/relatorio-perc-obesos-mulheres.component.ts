import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRelatorioPercObesosMulheres } from 'app/shared/model/relatorio-perc-obesos-mulheres.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RelatorioPercObesosMulheresService } from './relatorio-perc-obesos-mulheres.service';
import { RelatorioPercObesosMulheresDeleteDialogComponent } from './relatorio-perc-obesos-mulheres-delete-dialog.component';

@Component({
  selector: 'jhi-relatorio-perc-obesos-mulheres',
  templateUrl: './relatorio-perc-obesos-mulheres.component.html',
})
export class RelatorioPercObesosMulheresComponent implements OnInit, OnDestroy {
  relatorioPercObesosMulheres: IRelatorioPercObesosMulheres[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected relatorioPercObesosMulheresService: RelatorioPercObesosMulheresService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.relatorioPercObesosMulheres = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.relatorioPercObesosMulheresService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IRelatorioPercObesosMulheres[]>) => this.paginateRelatorioPercObesosMulheres(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.relatorioPercObesosMulheres = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRelatorioPercObesosMulheres();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRelatorioPercObesosMulheres): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRelatorioPercObesosMulheres(): void {
    this.eventSubscriber = this.eventManager.subscribe('relatorioPercObesosMulheresListModification', () => this.reset());
  }

  delete(relatorioPercObesosMulheres: IRelatorioPercObesosMulheres): void {
    const modalRef = this.modalService.open(RelatorioPercObesosMulheresDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.relatorioPercObesosMulheres = relatorioPercObesosMulheres;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRelatorioPercObesosMulheres(data: IRelatorioPercObesosMulheres[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.relatorioPercObesosMulheres.push(data[i]);
      }
    }
  }
}
