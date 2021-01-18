import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioMediaIdadeTipoSangueComponent } from 'app/entities/relatorio-media-idade-tipo-sangue/relatorio-media-idade-tipo-sangue.component';
import { RelatorioMediaIdadeTipoSangueService } from 'app/entities/relatorio-media-idade-tipo-sangue/relatorio-media-idade-tipo-sangue.service';
import { RelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';

describe('Component Tests', () => {
  describe('RelatorioMediaIdadeTipoSangue Management Component', () => {
    let comp: RelatorioMediaIdadeTipoSangueComponent;
    let fixture: ComponentFixture<RelatorioMediaIdadeTipoSangueComponent>;
    let service: RelatorioMediaIdadeTipoSangueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioMediaIdadeTipoSangueComponent],
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
        .overrideTemplate(RelatorioMediaIdadeTipoSangueComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelatorioMediaIdadeTipoSangueComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelatorioMediaIdadeTipoSangueService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RelatorioMediaIdadeTipoSangue(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.relatorioMediaIdadeTipoSangues && comp.relatorioMediaIdadeTipoSangues[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RelatorioMediaIdadeTipoSangue(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.relatorioMediaIdadeTipoSangues && comp.relatorioMediaIdadeTipoSangues[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RelatorioMediaIdadeTipoSangue(123)],
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
      expect(comp.relatorioMediaIdadeTipoSangues && comp.relatorioMediaIdadeTipoSangues[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
