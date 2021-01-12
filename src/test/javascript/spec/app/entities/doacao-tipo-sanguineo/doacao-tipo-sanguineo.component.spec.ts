import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BancoSangueTestModule } from '../../../test.module';
import { DoacaoTipoSanguineoComponent } from 'app/entities/doacao-tipo-sanguineo/doacao-tipo-sanguineo.component';
import { DoacaoTipoSanguineoService } from 'app/entities/doacao-tipo-sanguineo/doacao-tipo-sanguineo.service';
import { DoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';

describe('Component Tests', () => {
  describe('DoacaoTipoSanguineo Management Component', () => {
    let comp: DoacaoTipoSanguineoComponent;
    let fixture: ComponentFixture<DoacaoTipoSanguineoComponent>;
    let service: DoacaoTipoSanguineoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [DoacaoTipoSanguineoComponent],
      })
        .overrideTemplate(DoacaoTipoSanguineoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DoacaoTipoSanguineoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DoacaoTipoSanguineoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DoacaoTipoSanguineo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.doacaoTipoSanguineos && comp.doacaoTipoSanguineos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
