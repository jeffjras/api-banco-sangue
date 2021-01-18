import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RelatorioPercObesosMulheresService } from 'app/entities/relatorio-perc-obesos-mulheres/relatorio-perc-obesos-mulheres.service';
import { IRelatorioPercObesosMulheres, RelatorioPercObesosMulheres } from 'app/shared/model/relatorio-perc-obesos-mulheres.model';

describe('Service Tests', () => {
  describe('RelatorioPercObesosMulheres Service', () => {
    let injector: TestBed;
    let service: RelatorioPercObesosMulheresService;
    let httpMock: HttpTestingController;
    let elemDefault: IRelatorioPercObesosMulheres;
    let expectedResult: IRelatorioPercObesosMulheres | IRelatorioPercObesosMulheres[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RelatorioPercObesosMulheresService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RelatorioPercObesosMulheres(0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RelatorioPercObesosMulheres', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RelatorioPercObesosMulheres()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RelatorioPercObesosMulheres', () => {
        const returnedFromService = Object.assign(
          {
            percentual: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RelatorioPercObesosMulheres', () => {
        const returnedFromService = Object.assign(
          {
            percentual: 1,
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

      it('should delete a RelatorioPercObesosMulheres', () => {
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
