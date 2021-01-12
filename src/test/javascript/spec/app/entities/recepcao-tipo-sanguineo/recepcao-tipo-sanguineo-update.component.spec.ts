import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RecepcaoTipoSanguineoUpdateComponent } from 'app/entities/recepcao-tipo-sanguineo/recepcao-tipo-sanguineo-update.component';
import { RecepcaoTipoSanguineoService } from 'app/entities/recepcao-tipo-sanguineo/recepcao-tipo-sanguineo.service';
import { RecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';

describe('Component Tests', () => {
  describe('RecepcaoTipoSanguineo Management Update Component', () => {
    let comp: RecepcaoTipoSanguineoUpdateComponent;
    let fixture: ComponentFixture<RecepcaoTipoSanguineoUpdateComponent>;
    let service: RecepcaoTipoSanguineoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RecepcaoTipoSanguineoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RecepcaoTipoSanguineoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RecepcaoTipoSanguineoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RecepcaoTipoSanguineoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RecepcaoTipoSanguineo(123);
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
        const entity = new RecepcaoTipoSanguineo();
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
