import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorComponent } from 'app/entities/relatorio-qtde-doadores-para-cada-tipo-receptor/relatorio-qtde-doadores-para-cada-tipo-receptor.component';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorService } from 'app/entities/relatorio-qtde-doadores-para-cada-tipo-receptor/relatorio-qtde-doadores-para-cada-tipo-receptor.service';
import { RelatorioQtdeDoadoresParaCadaTipoReceptor } from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';

describe('Component Tests', () => {
  describe('RelatorioQtdeDoadoresParaCadaTipoReceptor Management Component', () => {
    let comp: RelatorioQtdeDoadoresParaCadaTipoReceptorComponent;
    let fixture: ComponentFixture<RelatorioQtdeDoadoresParaCadaTipoReceptorComponent>;
    let service: RelatorioQtdeDoadoresParaCadaTipoReceptorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioQtdeDoadoresParaCadaTipoReceptorComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(RelatorioQtdeDoadoresParaCadaTipoReceptorComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelatorioQtdeDoadoresParaCadaTipoReceptorComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioQtdeDoadoresParaCadaTipoReceptorService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RelatorioQtdeDoadoresParaCadaTipoReceptor(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.relatorioQtdeDoadoresParaCadaTipoReceptors && comp.relatorioQtdeDoadoresParaCadaTipoReceptors[0]).toEqual(
        jasmine.objectContaining({ id: 123 })
      );
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RelatorioQtdeDoadoresParaCadaTipoReceptor(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.relatorioQtdeDoadoresParaCadaTipoReceptors && comp.relatorioQtdeDoadoresParaCadaTipoReceptors[0]).toEqual(
        jasmine.objectContaining({ id: 123 })
      );
    });

    it('should re-initialize the page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RelatorioQtdeDoadoresParaCadaTipoReceptor(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);
      comp.reset();

      // THEN
      expect(comp.page).toEqual(0);
      expect(service.query).toHaveBeenCalledTimes(2);
      expect(comp.relatorioQtdeDoadoresParaCadaTipoReceptors && comp.relatorioQtdeDoadoresParaCadaTipoReceptors[0]).toEqual(
        jasmine.objectContaining({ id: 123 })
      );
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
  });
});
