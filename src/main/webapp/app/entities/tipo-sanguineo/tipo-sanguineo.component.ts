import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TipoSanguineoService } from './tipo-sanguineo.service';
import { TipoSanguineoDeleteDialogComponent } from './tipo-sanguineo-delete-dialog.component';

@Component({
  selector: 'jhi-tipo-sanguineo',
  templateUrl: './tipo-sanguineo.component.html',
})
export class TipoSanguineoComponent implements OnInit, OnDestroy {
  tipoSanguineos: ITipoSanguineo[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected tipoSanguineoService: TipoSanguineoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.tipoSanguineos = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.tipoSanguineoService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ITipoSanguineo[]>) => this.paginateTipoSanguineos(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.tipoSanguineos = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTipoSanguineos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITipoSanguineo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTipoSanguineos(): void {
    this.eventSubscriber = this.eventManager.subscribe('tipoSanguineoListModification', () => this.reset());
  }

  delete(tipoSanguineo: ITipoSanguineo): void {
    const modalRef = this.modalService.open(TipoSanguineoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tipoSanguineo = tipoSanguineo;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTipoSanguineos(data: ITipoSanguineo[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.tipoSanguineos.push(data[i]);
      }
    }
  }
}
