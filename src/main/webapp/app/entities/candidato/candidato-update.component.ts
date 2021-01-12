import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICandidato, Candidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from './candidato.service';
import { ITipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';
import { TipoSanguineoService } from 'app/entities/tipo-sanguineo/tipo-sanguineo.service';

@Component({
  selector: 'jhi-candidato-update',
  templateUrl: './candidato-update.component.html',
})
export class CandidatoUpdateComponent implements OnInit {
  isSaving = false;
  tiposanguineos: ITipoSanguineo[] = [];
  dataNascDp: any;

  editForm = this.fb.group({
    id: [],
    nome: [],
    cpf: [],
    rg: [],
    dataNasc: [],
    sexo: [],
    mae: [],
    pai: [],
    email: [],
    cep: [],
    endereco: [],
    numero: [],
    bairro: [],
    cidade: [],
    estado: [],
    telefoneFixo: [],
    celular: [],
    altura: [],
    peso: [],
    tipoSangue: [],
    tipoSanguineoId: [],
  });

  constructor(
    protected candidatoService: CandidatoService,
    protected tipoSanguineoService: TipoSanguineoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ candidato }) => {
      this.updateForm(candidato);

      this.tipoSanguineoService.query().subscribe((res: HttpResponse<ITipoSanguineo[]>) => (this.tiposanguineos = res.body || []));
    });
  }

  updateForm(candidato: ICandidato): void {
    this.editForm.patchValue({
      id: candidato.id,
      nome: candidato.nome,
      cpf: candidato.cpf,
      rg: candidato.rg,
      dataNasc: candidato.dataNasc,
      sexo: candidato.sexo,
      mae: candidato.mae,
      pai: candidato.pai,
      email: candidato.email,
      cep: candidato.cep,
      endereco: candidato.endereco,
      numero: candidato.numero,
      bairro: candidato.bairro,
      cidade: candidato.cidade,
      estado: candidato.estado,
      telefoneFixo: candidato.telefoneFixo,
      celular: candidato.celular,
      altura: candidato.altura,
      peso: candidato.peso,
      tipoSangue: candidato.tipoSangue,
      tipoSanguineoId: candidato.tipoSanguineoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const candidato = this.createFromForm();
    if (candidato.id !== undefined) {
      this.subscribeToSaveResponse(this.candidatoService.update(candidato));
    } else {
      this.subscribeToSaveResponse(this.candidatoService.create(candidato));
    }
  }

  private createFromForm(): ICandidato {
    return {
      ...new Candidato(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      cpf: this.editForm.get(['cpf'])!.value,
      rg: this.editForm.get(['rg'])!.value,
      dataNasc: this.editForm.get(['dataNasc'])!.value,
      sexo: this.editForm.get(['sexo'])!.value,
      mae: this.editForm.get(['mae'])!.value,
      pai: this.editForm.get(['pai'])!.value,
      email: this.editForm.get(['email'])!.value,
      cep: this.editForm.get(['cep'])!.value,
      endereco: this.editForm.get(['endereco'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      bairro: this.editForm.get(['bairro'])!.value,
      cidade: this.editForm.get(['cidade'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      telefoneFixo: this.editForm.get(['telefoneFixo'])!.value,
      celular: this.editForm.get(['celular'])!.value,
      altura: this.editForm.get(['altura'])!.value,
      peso: this.editForm.get(['peso'])!.value,
      tipoSangue: this.editForm.get(['tipoSangue'])!.value,
      tipoSanguineoId: this.editForm.get(['tipoSanguineoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICandidato>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ITipoSanguineo): any {
    return item.id;
  }
}
