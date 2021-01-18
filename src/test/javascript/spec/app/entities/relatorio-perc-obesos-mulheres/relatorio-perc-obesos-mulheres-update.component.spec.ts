import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioPercObesosMulheresUpdateComponent } from 'app/entities/relatorio-perc-obesos-mulheres/relatorio-perc-obesos-mulheres-update.component';
import { RelatorioPercObesosMulheresService } from 'app/entities/relatorio-perc-obesos-mulheres/relatorio-perc-obesos-mulheres.service';
import { RelatorioPercObesosMulheres } from 'app/shared/model/relatorio-perc-obesos-mulheres.model';

describe('Component Tests', () => {
  describe('RelatorioPercObesosMulheres Management Update Component', () => {
    let comp: RelatorioPercObesosMulheresUpdateComponent;
    let fixture: ComponentFixture<RelatorioPercObesosMulheresUpdateComponent>;
    let service: RelatorioPercObesosMulheresService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioPercObesosMulheresUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RelatorioPercObesosMulheresUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelatorioPercObesosMulheresUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioPercObesosMulheresService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RelatorioPercObesosMulheres(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new RelatorioPercObesosMulheres();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
