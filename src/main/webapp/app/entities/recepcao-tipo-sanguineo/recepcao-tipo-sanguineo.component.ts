import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';
import { RecepcaoTipoSanguineoService } from './recepcao-tipo-sanguineo.service';
import { RecepcaoTipoSanguineoDeleteDialogComponent } from './recepcao-tipo-sanguineo-delete-dialog.component';

@Component({
  selector: 'jhi-recepcao-tipo-sanguineo',
  templateUrl: './recepcao-tipo-sanguineo.component.html',
})
export class RecepcaoTipoSanguineoComponent implements OnInit, OnDestroy {
  recepcaoTipoSanguineos?: IRecepcaoTipoSanguineo[];
  eventSubscriber?: Subscription;

  constructor(
    protected recepcaoTipoSanguineoService: RecepcaoTipoSanguineoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.recepcaoTipoSanguineoService
      .query()
      .subscribe((res: HttpResponse<IRecepcaoTipoSanguineo[]>) => (this.recepcaoTipoSanguineos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRecepcaoTipoSanguineos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRecepcaoTipoSanguineo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRecepcaoTipoSanguineos(): void {
    this.eventSubscriber = this.eventManager.subscribe('recepcaoTipoSanguineoListModification', () => this.loadAll());
  }

  delete(recepcaoTipoSanguineo: IRecepcaoTipoSanguineo): void {
    const modalRef = this.modalService.open(RecepcaoTipoSanguineoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.recepcaoTipoSanguineo = recepcaoTipoSanguineo;
  }
}
