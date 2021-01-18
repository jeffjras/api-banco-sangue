import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioQtdCandPorEstadoDetailComponent } from 'app/entities/relatorio-qtd-cand-por-estado/relatorio-qtd-cand-por-estado-detail.component';
import { RelatorioQtdCandPorEstado } from 'app/shared/model/relatorio-qtd-cand-por-estado.model';

describe('Component Tests', () => {
  describe('RelatorioQtdCandPorEstado Management Detail Component', () => {
    let comp: RelatorioQtdCandPorEstadoDetailComponent;
    let fixture: ComponentFixture<RelatorioQtdCandPorEstadoDetailComponent>;
    const route = ({ data: of({ relatorioQtdCandPorEstado: new RelatorioQtdCandPorEstado(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioQtdCandPorEstadoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RelatorioQtdCandPorEstadoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelatorioQtdCandPorEstadoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load relatorioQtdCandPorEstado on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.relatorioQtdCandPorEstado).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
