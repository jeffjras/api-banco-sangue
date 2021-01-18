import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRelatorioMediaIdadeTipoSangue, RelatorioMediaIdadeTipoSangue } from 'app/shared/model/relatorio-media-idade-tipo-sangue.model';
import { RelatorioMediaIdadeTipoSangueService } from './relatorio-media-idade-tipo-sangue.service';

@Component({
  selector: 'jhi-relatorio-media-idade-tipo-sangue-update',
  templateUrl: './relatorio-media-idade-tipo-sangue-update.component.html',
})
export class RelatorioMediaIdadeTipoSangueUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tipoSangue: [],
    mediaTipo: [],
  });

  constructor(
    protected relatorioMediaIdadeTipoSangueService: RelatorioMediaIdadeTipoSangueService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relatorioMediaIdadeTipoSangue }) => {
      this.updateForm(relatorioMediaIdadeTipoSangue);
    });
  }

  updateForm(relatorioMediaIdadeTipoSangue: IRelatorioMediaIdadeTipoSangue): void {
    this.editForm.patchValue({
      id: relatorioMediaIdadeTipoSangue.id,
      tipoSangue: relatorioMediaIdadeTipoSangue.tipoSangue,
      mediaTipo: relatorioMediaIdadeTipoSangue.mediaTipo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const relatorioMediaIdadeTipoSangue = this.createFromForm();
    if (relatorioMediaIdadeTipoSangue.id !== undefined) {
      this.subscribeToSaveResponse(this.relatorioMediaIdadeTipoSangueService.update(relatorioMediaIdadeTipoSangue));
    } else {
      this.subscribeToSaveResponse(this.relatorioMediaIdadeTipoSangueService.create(relatorioMediaIdadeTipoSangue));
    }
  }

  private createFromForm(): IRelatorioMediaIdadeTipoSangue {
    return {
      ...new RelatorioMediaIdadeTipoSangue(),
      id: this.editForm.get(['id'])!.value,
      tipoSangue: this.editForm.get(['tipoSangue'])!.value,
      mediaTipo: this.editForm.get(['mediaTipo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRelatorioMediaIdadeTipoSangue>>): void {
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
