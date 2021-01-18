import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioPercObesosHomensUpdateComponent } from 'app/entities/relatorio-perc-obesos-homens/relatorio-perc-obesos-homens-update.component';
import { RelatorioPercObesosHomensService } from 'app/entities/relatorio-perc-obesos-homens/relatorio-perc-obesos-homens.service';
import { RelatorioPercObesosHomens } from 'app/shared/model/relatorio-perc-obesos-homens.model';

describe('Component Tests', () => {
  describe('RelatorioPercObesosHomens Management Update Component', () => {
    let comp: RelatorioPercObesosHomensUpdateComponent;
    let fixture: ComponentFixture<RelatorioPercObesosHomensUpdateComponent>;
    let service: RelatorioPercObesosHomensService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioPercObesosHomensUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RelatorioPercObesosHomensUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelatorioPercObesosHomensUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioPercObesosHomensService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RelatorioPercObesosHomens(123);
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
        const entity = new RelatorioPercObesosHomens();
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
