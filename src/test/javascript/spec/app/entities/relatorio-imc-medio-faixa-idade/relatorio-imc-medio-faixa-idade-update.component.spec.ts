import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioImcMedioFaixaIdadeUpdateComponent } from 'app/entities/relatorio-imc-medio-faixa-idade/relatorio-imc-medio-faixa-idade-update.component';
import { RelatorioImcMedioFaixaIdadeService } from 'app/entities/relatorio-imc-medio-faixa-idade/relatorio-imc-medio-faixa-idade.service';
import { RelatorioImcMedioFaixaIdade } from 'app/shared/model/relatorio-imc-medio-faixa-idade.model';

describe('Component Tests', () => {
  describe('RelatorioImcMedioFaixaIdade Management Update Component', () => {
    let comp: RelatorioImcMedioFaixaIdadeUpdateComponent;
    let fixture: ComponentFixture<RelatorioImcMedioFaixaIdadeUpdateComponent>;
    let service: RelatorioImcMedioFaixaIdadeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioImcMedioFaixaIdadeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RelatorioImcMedioFaixaIdadeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelatorioImcMedioFaixaIdadeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioImcMedioFaixaIdadeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RelatorioImcMedioFaixaIdade(123);
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
        const entity = new RelatorioImcMedioFaixaIdade();
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
