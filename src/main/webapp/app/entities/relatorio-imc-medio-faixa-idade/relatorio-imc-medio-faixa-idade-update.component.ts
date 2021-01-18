import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRelatorioImcMedioFaixaIdade, RelatorioImcMedioFaixaIdade } from 'app/shared/model/relatorio-imc-medio-faixa-idade.model';
import { RelatorioImcMedioFaixaIdadeService } from './relatorio-imc-medio-faixa-idade.service';

@Component({
  selector: 'jhi-relatorio-imc-medio-faixa-idade-update',
  templateUrl: './relatorio-imc-medio-faixa-idade-update.component.html',
})
export class RelatorioImcMedioFaixaIdadeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    faixaEtaria: [],
    imcMedio: [],
  });

  constructor(
    protected relatorioImcMedioFaixaIdadeService: RelatorioImcMedioFaixaIdadeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relatorioImcMedioFaixaIdade }) => {
      this.updateForm(relatorioImcMedioFaixaIdade);
    });
  }

  updateForm(relatorioImcMedioFaixaIdade: IRelatorioImcMedioFaixaIdade): void {
    this.editForm.patchValue({
      id: relatorioImcMedioFaixaIdade.id,
      faixaEtaria: relatorioImcMedioFaixaIdade.faixaEtaria,
      imcMedio: relatorioImcMedioFaixaIdade.imcMedio,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const relatorioImcMedioFaixaIdade = this.createFromForm();
    if (relatorioImcMedioFaixaIdade.id !== undefined) {
      this.subscribeToSaveResponse(this.relatorioImcMedioFaixaIdadeService.update(relatorioImcMedioFaixaIdade));
    } else {
      this.subscribeToSaveResponse(this.relatorioImcMedioFaixaIdadeService.create(relatorioImcMedioFaixaIdade));
    }
  }

  private createFromForm(): IRelatorioImcMedioFaixaIdade {
    return {
      ...new RelatorioImcMedioFaixaIdade(),
      id: this.editForm.get(['id'])!.value,
      faixaEtaria: this.editForm.get(['faixaEtaria'])!.value,
      imcMedio: this.editForm.get(['imcMedio'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRelatorioImcMedioFaixaIdade>>): void {
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
}
