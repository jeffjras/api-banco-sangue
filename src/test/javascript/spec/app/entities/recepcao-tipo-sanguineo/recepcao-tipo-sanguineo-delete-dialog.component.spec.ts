import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BancoSangueTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { RecepcaoTipoSanguineoDeleteDialogComponent } from 'app/entities/recepcao-tipo-sanguineo/recepcao-tipo-sanguineo-delete-dialog.component';
import { RecepcaoTipoSanguineoService } from 'app/entities/recepcao-tipo-sanguineo/recepcao-tipo-sanguineo.service';

describe('Component Tests', () => {
  describe('RecepcaoTipoSanguineo Management Delete Component', () => {
    let comp: RecepcaoTipoSanguineoDeleteDialogComponent;
    let fixture: ComponentFixture<RecepcaoTipoSanguineoDeleteDialogComponent>;
    let service: RecepcaoTipoSanguineoService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RecepcaoTipoSanguineoDeleteDialogComponent],
      })
        .overrideTemplate(RecepcaoTipoSanguineoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RecepcaoTipoSanguineoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RecepcaoTipoSanguineoService);
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
