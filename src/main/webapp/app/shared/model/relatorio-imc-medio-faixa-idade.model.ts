export interface IRelatorioImcMedioFaixaIdade {
  id?: number;
  faixaEtaria?: string;
  imcMedio?: number;
}

export class RelatorioImcMedioFaixaIdade implements IRelatorioImcMedioFaixaIdade {
  constructor(public id?: number, public faixaEtaria?: string, public imcMedio?: number) {}
}
