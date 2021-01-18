import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioPercObesosMulheresDetailComponent } from 'app/entities/relatorio-perc-obesos-mulheres/relatorio-perc-obesos-mulheres-detail.component';
import { RelatorioPercObesosMulheres } from 'app/shared/model/relatorio-perc-obesos-mulheres.model';

describe('Component Tests', () => {
  describe('RelatorioPercObesosMulheres Management Detail Component', () => {
    let comp: RelatorioPercObesosMulheresDetailComponent;
    let fixture: ComponentFixture<RelatorioPercObesosMulheresDetailComponent>;
    const route = ({ data: of({ relatorioPercObesosMulheres: new RelatorioPercObesosMulheres(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioPercObesosMulheresDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RelatorioPercObesosMulheresDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelatorioPercObesosMulheresDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load relatorioPercObesosMulheres on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.relatorioPercObesosMulheres).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
