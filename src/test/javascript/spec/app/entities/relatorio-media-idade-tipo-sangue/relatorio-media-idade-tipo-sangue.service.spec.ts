import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RelatorioMediaIdadeTipoSangueService } from 'app/entities/relatorio-media-idade-tipo-sangue/relatorio-media-idade-tipo-sangue.service';
import { IRelatorioMediaIdadeTipoSangue, RelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';

describe('Service Tests', () => {
  describe('RelatorioMediaIdadeTipoSangue Service', () => {
    let injector: TestBed;
    let service: RelatorioMediaIdadeTipoSangueService;
    let httpMock: HttpTestingController;
    let elemDefault: IRelatorioMediaIdadeTipoSangue;
    let expectedResult: IRelatorioMediaIdadeTipoSangue | IRelatorioMediaIdadeTipoSangue[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RelatorioMediaIdadeTipoSangueService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RelatorioMediaIdadeTipoSangue(0, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RelatorioMediaIdadeTipoSangue', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RelatorioMediaIdadeTipoSangue()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RelatorioMediaIdadeTipoSangue', () => {
        const returnedFromService = Object.assign(
          {
            tipoSangue: 'BBBBBB',
            mediaTipo: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RelatorioMediaIdadeTipoSangue', () => {
        const returnedFromService = Object.assign(
          {
            tipoSangue: 'BBBBBB',
            mediaTipo: 1,
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

      it('should delete a RelatorioMediaIdadeTipoSangue', () => {
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
