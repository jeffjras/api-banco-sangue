import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorService } from 'app/entities/relatorio-qtde-doadores-para-cada-tipo-receptor/relatorio-qtde-doadores-para-cada-tipo-receptor.service';
import {
  IRelatorioQtdeDoadoresParaCadaTipoReceptor,
  RelatorioQtdeDoadoresParaCadaTipoReceptor,
} from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';

describe('Service Tests', () => {
  describe('RelatorioQtdeDoadoresParaCadaTipoReceptor Service', () => {
    let injector: TestBed;
    let service: RelatorioQtdeDoadoresParaCadaTipoReceptorService;
    let httpMock: HttpTestingController;
    let elemDefault: IRelatorioQtdeDoadoresParaCadaTipoReceptor;
    let expectedResult: IRelatorioQtdeDoadoresParaCadaTipoReceptor | IRelatorioQtdeDoadoresParaCadaTipoReceptor[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RelatorioQtdeDoadoresParaCadaTipoReceptorService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RelatorioQtdeDoadoresParaCadaTipoReceptor(0, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RelatorioQtdeDoadoresParaCadaTipoReceptor', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RelatorioQtdeDoadoresParaCadaTipoReceptor()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RelatorioQtdeDoadoresParaCadaTipoReceptor', () => {
        const returnedFromService = Object.assign(
          {
            sangue: 'BBBBBB',
            totalDoador: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RelatorioQtdeDoadoresParaCadaTipoReceptor', () => {
        const returnedFromService = Object.assign(
          {
            sangue: 'BBBBBB',
            totalDoador: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a RelatorioQtdeDoadoresParaCadaTipoReceptor', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
