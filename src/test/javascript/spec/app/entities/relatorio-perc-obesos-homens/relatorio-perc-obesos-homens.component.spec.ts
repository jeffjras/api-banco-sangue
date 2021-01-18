import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioPercObesosHomensComponent } from 'app/entities/relatorio-perc-obesos-homens/relatorio-perc-obesos-homens.component';
import { RelatorioPercObesosHomensService } from 'app/entities/relatorio-perc-obesos-homens/relatorio-perc-obesos-homens.service';
import { RelatorioPercObesosHomens } from 'app/shared/model/relatorio-perc-obesos-homens.model';

describe('Component Tests', () => {
  describe('RelatorioPercObesosHomens Management Component', () => {
    let comp: RelatorioPercObesosHomensComponent;
    let fixture: ComponentFixture<RelatorioPercObesosHomensComponent>;
    let service: RelatorioPercObesosHomensService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioPercObesosHomensComponent],
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
        .overrideTemplate(RelatorioPercObesosHomensComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelatorioPercObesosHomensComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioPercObesosHomensService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RelatorioPercObesosHomens(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.relatorioPercObesosHomens && comp.relatorioPercObesosHomens[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RelatorioPercObesosHomens(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.relatorioPercObesosHomens && comp.relatorioPercObesosHomens[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RelatorioPercObesosHomens(123)],
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
      expect(comp.relatorioPercObesosHomens && comp.relatorioPercObesosHomens[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
