import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioMediaIdadeTipoSangueUpdateComponent } from 'app/entities/relatorio-media-idade-tipo-sangue/relatorio-media-idade-tipo-sangue-update.component';
import { RelatorioMediaIdadeTipoSangueService } from 'app/entities/relatorio-media-idade-tipo-sangue/relatorio-media-idade-tipo-sangue.service';
import { RelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';

describe('Component Tests', () => {
  describe('RelatorioMediaIdadeTipoSangue Management Update Component', () => {
    let comp: RelatorioMediaIdadeTipoSangueUpdateComponent;
    let fixture: ComponentFixture<RelatorioMediaIdadeTipoSangueUpdateComponent>;
    let service: RelatorioMediaIdadeTipoSangueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioMediaIdadeTipoSangueUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RelatorioMediaIdadeTipoSangueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelatorioMediaIdadeTipoSangueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioMediaIdadeTipoSangueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RelatorioMediaIdadeTipoSangue(123);
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
        const entity = new RelatorioMediaIdadeTipoSangue();
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
