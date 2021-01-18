import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioPercObesosHomensDetailComponent } from 'app/entities/relatorio-perc-obesos-homens/relatorio-perc-obesos-homens-detail.component';
import { RelatorioPercObesosHomens } from 'app/shared/model/relatorio-perc-obesos-homens.model';

describe('Component Tests', () => {
  describe('RelatorioPercObesosHomens Management Detail Component', () => {
    let comp: RelatorioPercObesosHomensDetailComponent;
    let fixture: ComponentFixture<RelatorioPercObesosHomensDetailComponent>;
    const route = ({ data: of({ relatorioPercObesosHomens: new RelatorioPercObesosHomens(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioPercObesosHomensDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RelatorioPercObesosHomensDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelatorioPercObesosHomensDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load relatorioPercObesosHomens on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.relatorioPercObesosHomens).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
