import { ITipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';

export interface IDoacaoTipoSanguineo {
  id?: number;
  podeDoarPara?: string;
  listaTipoSanguineos?: ITipoSanguineo[];
}

export class DoacaoTipoSanguineo implements IDoacaoTipoSanguineo {
  constructor(public id?: number, public podeDoarPara?: string, public listaTipoSanguineos?: ITipoSanguineo[]) {}
}
