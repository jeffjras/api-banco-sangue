import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BancoSangueTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { RelatorioQtdCandPorEstadoDeleteDialogComponent } from 'app/entities/relatorio-qtd-cand-por-estado/relatorio-qtd-cand-por-estado-delete-dialog.component';
import { RelatorioQtdCandPorEstadoService } from 'app/entities/relatorio-qtd-cand-por-estado/relatorio-qtd-cand-por-estado.service';

describe('Component Tests', () => {
  describe('RelatorioQtdCandPorEstado Management Delete Component', () => {
    let comp: RelatorioQtdCandPorEstadoDeleteDialogComponent;
    let fixture: ComponentFixture<RelatorioQtdCandPorEstadoDeleteDialogComponent>;
    let service: RelatorioQtdCandPorEstadoService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioQtdCandPorEstadoDeleteDialogComponent],
      })
        .overrideTemplate(RelatorioQtdCandPorEstadoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelatorioQtdCandPorEstadoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioQtdCandPorEstadoService);
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
