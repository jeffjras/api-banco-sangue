import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RecepcaoTipoSanguineoService } from 'app/entities/recepcao-tipo-sanguineo/recepcao-tipo-sanguineo.service';
import { IRecepcaoTipoSanguineo, RecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';

describe('Service Tests', () => {
  describe('RecepcaoTipoSanguineo Service', () => {
    let injector: TestBed;
    let service: RecepcaoTipoSanguineoService;
    let httpMock: HttpTestingController;
    let elemDefault: IRecepcaoTipoSanguineo;
    let expectedResult: IRecepcaoTipoSanguineo | IRecepcaoTipoSanguineo[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RecepcaoTipoSanguineoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RecepcaoTipoSanguineo(0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RecepcaoTipoSanguineo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RecepcaoTipoSanguineo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RecepcaoTipoSanguineo', () => {
        const returnedFromService = Object.assign(
          {
            podeReceberDe: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RecepcaoTipoSanguineo', () => {
        const returnedFromService = Object.assign(
          {
            podeReceberDe: 'BBBBBB',
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

      it('should delete a RecepcaoTipoSanguineo', () => {
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
