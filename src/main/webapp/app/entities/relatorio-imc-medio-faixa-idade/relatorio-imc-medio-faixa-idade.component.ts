import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRelatorioImcMedioFaixaIdade } from 'app/shared/model/relatorio-imc-medio-faixa-idade.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RelatorioImcMedioFaixaIdadeService } from './relatorio-imc-medio-faixa-idade.service';
import { RelatorioImcMedioFaixaIdadeDeleteDialogComponent } from './relatorio-imc-medio-faixa-idade-delete-dialog.component';

@Component({
  selector: 'jhi-relatorio-imc-medio-faixa-idade',
  templateUrl: './relatorio-imc-medio-faixa-idade.component.html',
})
export class RelatorioImcMedioFaixaIdadeComponent implements OnInit, OnDestroy {
  relatorioImcMedioFaixaIdades: IRelatorioImcMedioFaixaIdade[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected relatorioImcMedioFaixaIdadeService: RelatorioImcMedioFaixaIdadeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.relatorioImcMedioFaixaIdades = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.relatorioImcMedioFaixaIdadeService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IRelatorioImcMedioFaixaIdade[]>) => this.paginateRelatorioImcMedioFaixaIdades(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.relatorioImcMedioFaixaIdades = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRelatorioImcMedioFaixaIdades();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRelatorioImcMedioFaixaIdade): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRelatorioImcMedioFaixaIdades(): void {
    this.eventSubscriber = this.eventManager.subscribe('relatorioImcMedioFaixaIdadeListModification', () => this.reset());
  }

  delete(relatorioImcMedioFaixaIdade: IRelatorioImcMedioFaixaIdade): void {
    const modalRef = this.modalService.open(RelatorioImcMedioFaixaIdadeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.relatorioImcMedioFaixaIdade = relatorioImcMedioFaixaIdade;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRelatorioImcMedioFaixaIdades(data: IRelatorioImcMedioFaixaIdade[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.relatorioImcMedioFaixaIdades.push(data[i]);
      }
    }
  }
}
