import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RelatorioPercObesosHomensService } from 'app/entities/relatorio-perc-obesos-homens/relatorio-perc-obesos-homens.service';
import { IRelatorioPercObesosHomens, RelatorioPercObesosHomens } from 'app/shared/model/relatorio-perc-obesos-homens.model';

describe('Service Tests', () => {
  describe('RelatorioPercObesosHomens Service', () => {
    let injector: TestBed;
    let service: RelatorioPercObesosHomensService;
    let httpMock: HttpTestingController;
    let elemDefault: IRelatorioPercObesosHomens;
    let expectedResult: IRelatorioPercObesosHomens | IRelatorioPercObesosHomens[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RelatorioPercObesosHomensService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RelatorioPercObesosHomens(0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RelatorioPercObesosHomens', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RelatorioPercObesosHomens()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RelatorioPercObesosHomens', () => {
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

      it('should return a list of RelatorioPercObesosHomens', () => {
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

      it('should delete a RelatorioPercObesosHomens', () => {
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
