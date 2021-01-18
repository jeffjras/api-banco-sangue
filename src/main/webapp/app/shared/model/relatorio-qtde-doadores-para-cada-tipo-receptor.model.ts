export interface IRelatorioQtdeDoadoresParaCadaTipoReceptor {
  id?: number;
  sangue?: string;
  totalDoador?: number;
}

export class RelatorioQtdeDoadoresParaCadaTipoReceptor implements IRelatorioQtdeDoadoresParaCadaTipoReceptor {
  constructor(public id?: number, public sangue?: string, public totalDoador?: number) {}
}
