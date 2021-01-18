import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioQtdCandPorEstadoUpdateComponent } from 'app/entities/relatorio-qtd-cand-por-estado/relatorio-qtd-cand-por-estado-update.component';
import { RelatorioQtdCandPorEstadoService } from 'app/entities/relatorio-qtd-cand-por-estado/relatorio-qtd-cand-por-estado.service';
import { RelatorioQtdCandPorEstado } from 'app/shared/model/relatorio-qtd-cand-por-estado.model';

describe('Component Tests', () => {
  describe('RelatorioQtdCandPorEstado Management Update Component', () => {
    let comp: RelatorioQtdCandPorEstadoUpdateComponent;
    let fixture: ComponentFixture<RelatorioQtdCandPorEstadoUpdateComponent>;
    let service: RelatorioQtdCandPorEstadoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioQtdCandPorEstadoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RelatorioQtdCandPorEstadoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelatorioQtdCandPorEstadoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioQtdCandPorEstadoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RelatorioQtdCandPorEstado(123);
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
        const entity = new RelatorioQtdCandPorEstado();
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
