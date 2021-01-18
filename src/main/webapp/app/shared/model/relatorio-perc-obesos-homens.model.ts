export interface IRelatorioPercObesosHomens {
  id?: number;
  percentual?: number;
}

export class RelatorioPercObesosHomens implements IRelatorioPercObesosHomens {
  constructor(public id?: number, public percentual?: number) {}
}
