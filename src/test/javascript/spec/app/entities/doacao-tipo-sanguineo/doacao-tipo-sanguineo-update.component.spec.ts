import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { DoacaoTipoSanguineoUpdateComponent } from 'app/entities/doacao-tipo-sanguineo/doacao-tipo-sanguineo-update.component';
import { DoacaoTipoSanguineoService } from 'app/entities/doacao-tipo-sanguineo/doacao-tipo-sanguineo.service';
import { DoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';

describe('Component Tests', () => {
  describe('DoacaoTipoSanguineo Management Update Component', () => {
    let comp: DoacaoTipoSanguineoUpdateComponent;
    let fixture: ComponentFixture<DoacaoTipoSanguineoUpdateComponent>;
    let service: DoacaoTipoSanguineoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [DoacaoTipoSanguineoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DoacaoTipoSanguineoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DoacaoTipoSanguineoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DoacaoTipoSanguineoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DoacaoTipoSanguineo(123);
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
        const entity = new DoacaoTipoSanguineo();
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
