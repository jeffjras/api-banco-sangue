export interface IRelatorioQtdCandPorEstado {
  id?: number;
  qtde?: number;
  estado?: string;
}

export class RelatorioQtdCandPorEstado implements IRelatorioQtdCandPorEstado {
  constructor(public id?: number, public qtde?: number, public estado?: string) {}
}
