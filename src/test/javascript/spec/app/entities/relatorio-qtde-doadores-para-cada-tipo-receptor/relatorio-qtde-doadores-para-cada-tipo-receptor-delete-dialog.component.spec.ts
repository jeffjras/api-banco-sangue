import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BancoSangueTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent } from 'app/entities/relatorio-qtde-doadores-para-cada-tipo-receptor/relatorio-qtde-doadores-para-cada-tipo-receptor-delete-dialog.component';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorService } from 'app/entities/relatorio-qtde-doadores-para-cada-tipo-receptor/relatorio-qtde-doadores-para-cada-tipo-receptor.service';

describe('Component Tests', () => {
  describe('RelatorioQtdeDoadoresParaCadaTipoReceptor Management Delete Component', () => {
    let comp: RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent;
    let fixture: ComponentFixture<RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent>;
    let service: RelatorioQtdeDoadoresParaCadaTipoReceptorService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent],
      })
        .overrideTemplate(RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelatorioQtdeDoadoresParaCadaTipoReceptorDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioQtdeDoadoresParaCadaTipoReceptorService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
