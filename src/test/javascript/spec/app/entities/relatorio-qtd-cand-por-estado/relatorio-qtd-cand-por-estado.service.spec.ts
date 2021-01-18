import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RelatorioQtdCandPorEstadoService } from 'app/entities/relatorio-qtd-cand-por-estado/relatorio-qtd-cand-por-estado.service';
import { IRelatorioQtdCandPorEstado, RelatorioQtdCandPorEstado } from 'app/shared/model/relatorio-qtd-cand-por-estado.model';

describe('Service Tests', () => {
  describe('RelatorioQtdCandPorEstado Service', () => {
    let injector: TestBed;
    let service: RelatorioQtdCandPorEstadoService;
    let httpMock: HttpTestingController;
    let elemDefault: IRelatorioQtdCandPorEstado;
    let expectedResult: IRelatorioQtdCandPorEstado | IRelatorioQtdCandPorEstado[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RelatorioQtdCandPorEstadoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RelatorioQtdCandPorEstado(0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RelatorioQtdCandPorEstado', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RelatorioQtdCandPorEstado()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RelatorioQtdCandPorEstado', () => {
        const returnedFromService = Object.assign(
          {
            qtde: 1,
            estado: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RelatorioQtdCandPorEstado', () => {
        const returnedFromService = Object.assign(
          {
            qtde: 1,
            estado: 'BBBBBB',
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

      it('should delete a RelatorioQtdCandPorEstado', () => {
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
