export interface IRelatorioPercObesosMulheres {
  id?: number;
  percentual?: number;
}

export class RelatorioPercObesosMulheres implements IRelatorioPercObesosMulheres {
  constructor(public id?: number, public percentual?: number) {}
}
