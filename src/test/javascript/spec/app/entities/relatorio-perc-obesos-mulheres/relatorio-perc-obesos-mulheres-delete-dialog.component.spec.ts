import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BancoSangueTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { RelatorioPercObesosMulheresDeleteDialogComponent } from 'app/entities/relatorio-perc-obesos-mulheres/relatorio-perc-obesos-mulheres-delete-dialog.component';
import { RelatorioPercObesosMulheresService } from 'app/entities/relatorio-perc-obesos-mulheres/relatorio-perc-obesos-mulheres.service';

describe('Component Tests', () => {
  describe('RelatorioPercObesosMulheres Management Delete Component', () => {
    let comp: RelatorioPercObesosMulheresDeleteDialogComponent;
    let fixture: ComponentFixture<RelatorioPercObesosMulheresDeleteDialogComponent>;
    let service: RelatorioPercObesosMulheresService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioPercObesosMulheresDeleteDialogComponent],
      })
        .overrideTemplate(RelatorioPercObesosMulheresDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelatorioPercObesosMulheresDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioPercObesosMulheresService);
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
