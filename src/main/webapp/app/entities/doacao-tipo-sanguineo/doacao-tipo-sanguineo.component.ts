import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';
import { DoacaoTipoSanguineoService } from './doacao-tipo-sanguineo.service';
import { DoacaoTipoSanguineoDeleteDialogComponent } from './doacao-tipo-sanguineo-delete-dialog.component';

@Component({
  selector: 'jhi-doacao-tipo-sanguineo',
  templateUrl: './doacao-tipo-sanguineo.component.html',
})
export class DoacaoTipoSanguineoComponent implements OnInit, OnDestroy {
  doacaoTipoSanguineos?: IDoacaoTipoSanguineo[];
  eventSubscriber?: Subscription;

  constructor(
    protected doacaoTipoSanguineoService: DoacaoTipoSanguineoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.doacaoTipoSanguineoService
      .query()
      .subscribe((res: HttpResponse<IDoacaoTipoSanguineo[]>) => (this.doacaoTipoSanguineos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDoacaoTipoSanguineos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDoacaoTipoSanguineo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDoacaoTipoSanguineos(): void {
    this.eventSubscriber = this.eventManager.subscribe('doacaoTipoSanguineoListModification', () => this.loadAll());
  }

  delete(doacaoTipoSanguineo: IDoacaoTipoSanguineo): void {
    const modalRef = this.modalService.open(DoacaoTipoSanguineoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.doacaoTipoSanguineo = doacaoTipoSanguineo;
  }
}
