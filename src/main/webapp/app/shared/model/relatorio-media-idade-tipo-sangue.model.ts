export interface IRelatorioMediaIdadeTipoSangue {
  id?: number;
  tipoSangue?: string;
  mediaTipo?: number;
}

export class RelatorioMediaIdadeTipoSangue implements IRelatorioMediaIdadeTipoSangue {
  constructor(public id?: number, public tipoSangue?: string, public mediaTipo?: number) {}
}
