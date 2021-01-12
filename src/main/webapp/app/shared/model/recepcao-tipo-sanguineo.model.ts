import { ITipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';

export interface IRecepcaoTipoSanguineo {
  id?: number;
  podeReceberDe?: string;
  listaTipoSanguineos?: ITipoSanguineo[];
}

export class RecepcaoTipoSanguineo implements IRecepcaoTipoSanguineo {
  constructor(public id?: number, public podeReceberDe?: string, public listaTipoSanguineos?: ITipoSanguineo[]) {}
}
