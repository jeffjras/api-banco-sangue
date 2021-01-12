import { Moment } from 'moment';

export interface ICandidato {
  id?: number;
  nome?: string;
  cpf?: string;
  rg?: string;
  dataNasc?: Moment;
  sexo?: string;
  mae?: string;
  pai?: string;
  email?: string;
  cep?: string;
  endereco?: string;
  numero?: string;
  bairro?: string;
  cidade?: string;
  estado?: string;
  telefoneFixo?: string;
  celular?: string;
  altura?: number;
  peso?: number;
  tipoSangue?: string;
  tipoSanguineoId?: number;
}

export class Candidato implements ICandidato {
  constructor(
    public id?: number,
    public nome?: string,
    public cpf?: string,
    public rg?: string,
    public dataNasc?: Moment,
    public sexo?: string,
    public mae?: string,
    public pai?: string,
    public email?: string,
    public cep?: string,
    public endereco?: string,
    public numero?: string,
    public bairro?: string,
    public cidade?: string,
    public estado?: string,
    public telefoneFixo?: string,
    public celular?: string,
    public altura?: number,
    public peso?: number,
    public tipoSangue?: string,
    public tipoSanguineoId?: number
  ) {}
}
