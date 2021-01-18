import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent } from 'app/entities/relatorio-qtde-doadores-para-cada-tipo-receptor/relatorio-qtde-doadores-para-cada-tipo-receptor-update.component';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorService } from 'app/entities/relatorio-qtde-doadores-para-cada-tipo-receptor/relatorio-qtde-doadores-para-cada-tipo-receptor.service';
import { RelatorioQtdeDoadoresParaCadaTipoReceptor } from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';

describe('Component Tests', () => {
  describe('RelatorioQtdeDoadoresParaCadaTipoReceptor Management Update Component', () => {
    let comp: RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent;
    let fixture: ComponentFixture<RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent>;
    let service: RelatorioQtdeDoadoresParaCadaTipoReceptorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioQtdeDoadoresParaCadaTipoReceptorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RelatorioQtdeDoadoresParaCadaTipoReceptor(123);
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
        const entity = new RelatorioQtdeDoadoresParaCadaTipoReceptor();
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
