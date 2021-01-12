import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BancoSangueTestModule } from '../../../test.module';
import { RecepcaoTipoSanguineoComponent } from 'app/entities/recepcao-tipo-sanguineo/recepcao-tipo-sanguineo.component';
import { RecepcaoTipoSanguineoService } from 'app/entities/recepcao-tipo-sanguineo/recepcao-tipo-sanguineo.service';
import { RecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';

describe('Component Tests', () => {
  describe('RecepcaoTipoSanguineo Management Component', () => {
    let comp: RecepcaoTipoSanguineoComponent;
    let fixture: ComponentFixture<RecepcaoTipoSanguineoComponent>;
    let service: RecepcaoTipoSanguineoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RecepcaoTipoSanguineoComponent],
      })
        .overrideTemplate(RecepcaoTipoSanguineoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RecepcaoTipoSanguineoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RecepcaoTipoSanguineoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RecepcaoTipoSanguineo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.recepcaoTipoSanguineos && comp.recepcaoTipoSanguineos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
