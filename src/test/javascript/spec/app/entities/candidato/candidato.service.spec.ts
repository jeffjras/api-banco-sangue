import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CandidatoService } from 'app/entities/candidato/candidato.service';
import { ICandidato, Candidato } from 'app/shared/model/candidato.model';

describe('Service Tests', () => {
  describe('Candidato Service', () => {
    let injector: TestBed;
    let service: CandidatoService;
    let httpMock: HttpTestingController;
    let elemDefault: ICandidato;
    let expectedResult: ICandidato | ICandidato[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CandidatoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Candidato(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataNasc: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Candidato', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataNasc: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNasc: currentDate,
          },
          returnedFromService
        );

        service.create(new Candidato()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Candidato', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            cpf: 'BBBBBB',
            rg: 'BBBBBB',
            dataNasc: currentDate.format(DATE_FORMAT),
            sexo: 'BBBBBB',
            mae: 'BBBBBB',
            pai: 'BBBBBB',
            email: 'BBBBBB',
            cep: 'BBBBBB',
            endereco: 'BBBBBB',
            numero: 'BBBBBB',
            bairro: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            telefoneFixo: 'BBBBBB',
            celular: 'BBBBBB',
            altura: 1,
            peso: 1,
            tipoSangue: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNasc: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Candidato', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            cpf: 'BBBBBB',
            rg: 'BBBBBB',
            dataNasc: currentDate.format(DATE_FORMAT),
            sexo: 'BBBBBB',
            mae: 'BBBBBB',
            pai: 'BBBBBB',
            email: 'BBBBBB',
            cep: 'BBBBBB',
            endereco: 'BBBBBB',
            numero: 'BBBBBB',
            bairro: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            telefoneFixo: 'BBBBBB',
            celular: 'BBBBBB',
            altura: 1,
            peso: 1,
            tipoSangue: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNasc: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Candidato', () => {
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
