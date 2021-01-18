import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RelatorioMediaIdadeTipoSangueDetailComponent } from 'app/entities/relatorio-media-idade-tipo-sangue/relatorio-media-idade-tipo-sangue-detail.component';
import { RelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';

describe('Component Tests', () => {
  describe('RelatorioMediaIdadeTipoSangue Management Detail Component', () => {
    let comp: RelatorioMediaIdadeTipoSangueDetailComponent;
    let fixture: ComponentFixture<RelatorioMediaIdadeTipoSangueDetailComponent>;
    const route = ({ data: of({ relatorioMediaIdadeTipoSangue: new RelatorioMediaIdadeTipoSangue(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RelatorioMediaIdadeTipoSangueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RelatorioMediaIdadeTipoSangueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelatorioMediaIdadeTipoSangueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load relatorioMediaIdadeTipoSangue on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.relatorioMediaIdadeTipoSangue).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
