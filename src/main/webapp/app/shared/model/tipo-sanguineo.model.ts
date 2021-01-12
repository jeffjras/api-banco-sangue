import { ICandidato } from 'app/shared/model/candidato.model';

export interface ITipoSanguineo {
  id?: number;
  descricao?: string;
  listaCandidatos?: ICandidato[];
  doacaoTipoSanguineoId?: number;
  recepcaoTipoSanguineoId?: number;
}

export class TipoSanguineo implements ITipoSanguineo {
  constructor(
    public id?: number,
    public descricao?: string,
    public listaCandidatos?: ICandidato[],
    public doacaoTipoSanguineoId?: number,
    public recepcaoTipoSanguineoId?: number
  ) {}
}
