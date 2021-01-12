import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BancoSangueTestModule } from '../../../test.module';
import { RecepcaoTipoSanguineoDetailComponent } from 'app/entities/recepcao-tipo-sanguineo/recepcao-tipo-sanguineo-detail.component';
import { RecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';

describe('Component Tests', () => {
  describe('RecepcaoTipoSanguineo Management Detail Component', () => {
    let comp: RecepcaoTipoSanguineoDetailComponent;
    let fixture: ComponentFixture<RecepcaoTipoSanguineoDetailComponent>;
    const route = ({ data: of({ recepcaoTipoSanguineo: new RecepcaoTipoSanguineo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BancoSangueTestModule],
        declarations: [RecepcaoTipoSanguineoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RecepcaoTipoSanguineoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RecepcaoTipoSanguineoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load recepcaoTipoSanguineo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.recepcaoTipoSanguineo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
