import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { DoacaoTipoSanguineoDetailComponent } from 'app/entities/doacao-tipo-sanguineo/doacao-tipo-sanguineo-detail.component';
import { DoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';

describe('Component Tests', () => {
  describe('DoacaoTipoSanguineo Management Detail Component', () => {
    let comp: DoacaoTipoSanguineoDetailComponent;
    let fixture: ComponentFixture<DoacaoTipoSanguineoDetailComponent>;
    const route = ({ data: of({ doacaoTipoSanguineo: new DoacaoTipoSanguineo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [DoacaoTipoSanguineoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DoacaoTipoSanguineoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DoacaoTipoSanguineoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load doacaoTipoSanguineo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.doacaoTipoSanguineo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
