import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRecepcaoTipoSanguineo, RecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';
import { RecepcaoTipoSanguineoService } from './recepcao-tipo-sanguineo.service';

@Component({
  selector: 'jhi-recepcao-tipo-sanguineo-update',
  templateUrl: './recepcao-tipo-sanguineo-update.component.html',
})
export class RecepcaoTipoSanguineoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    podeReceberDe: [],
  });

  constructor(
    protected recepcaoTipoSanguineoService: RecepcaoTipoSanguineoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ recepcaoTipoSanguineo }) => {
      this.updateForm(recepcaoTipoSanguineo);
    });
  }

  updateForm(recepcaoTipoSanguineo: IRecepcaoTipoSanguineo): void {
    this.editForm.patchValue({
      id: recepcaoTipoSanguineo.id,
      podeReceberDe: recepcaoTipoSanguineo.podeReceberDe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const recepcaoTipoSanguineo = this.createFromForm();
    if (recepcaoTipoSanguineo.id !== undefined) {
      this.subscribeToSaveResponse(this.recepcaoTipoSanguineoService.update(recepcaoTipoSanguineo));
    } else {
      this.subscribeToSaveResponse(this.recepcaoTipoSanguineoService.create(recepcaoTipoSanguineo));
    }
  }

  private createFromForm(): IRecepcaoTipoSanguineo {
    return {
      ...new RecepcaoTipoSanguineo(),
      id: this.editForm.get(['id'])!.value,
      podeReceberDe: this.editForm.get(['podeReceberDe'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecepcaoTipoSanguineo>>): void {
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
