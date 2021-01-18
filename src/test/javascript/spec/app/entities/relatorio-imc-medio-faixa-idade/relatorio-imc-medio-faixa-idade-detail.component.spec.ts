import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioImcMedioFaixaIdadeDetailComponent } from 'app/entities/relatorio-imc-medio-faixa-idade/relatorio-imc-medio-faixa-idade-detail.component';
import { RelatorioImcMedioFaixaIdade } from 'app/shared/model/relatorio-imc-medio-faixa-idade.model';

describe('Component Tests', () => {
  describe('RelatorioImcMedioFaixaIdade Management Detail Component', () => {
    let comp: RelatorioImcMedioFaixaIdadeDetailComponent;
    let fixture: ComponentFixture<RelatorioImcMedioFaixaIdadeDetailComponent>;
    const route = ({ data: of({ relatorioImcMedioFaixaIdade: new RelatorioImcMedioFaixaIdade(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioImcMedioFaixaIdadeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RelatorioImcMedioFaixaIdadeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelatorioImcMedioFaixaIdadeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load relatorioImcMedioFaixaIdade on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.relatorioImcMedioFaixaIdade).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
