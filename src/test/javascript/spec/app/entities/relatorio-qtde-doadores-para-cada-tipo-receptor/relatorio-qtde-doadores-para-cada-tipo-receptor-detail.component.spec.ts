import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent } from 'app/entities/relatorio-qtde-doadores-para-cada-tipo-receptor/relatorio-qtde-doadores-para-cada-tipo-receptor-detail.component';
import { RelatorioQtdeDoadoresParaCadaTipoReceptor } from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';

describe('Component Tests', () => {
  describe('RelatorioQtdeDoadoresParaCadaTipoReceptor Management Detail Component', () => {
    let comp: RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent;
    let fixture: ComponentFixture<RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent>;
    const route = ({
      data: of({ relatorioQtdeDoadoresParaCadaTipoReceptor: new RelatorioQtdeDoadoresParaCadaTipoReceptor(123) }),
    } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelatorioQtdeDoadoresParaCadaTipoReceptorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load relatorioQtdeDoadoresParaCadaTipoReceptor on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.relatorioQtdeDoadoresParaCadaTipoReceptor).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
