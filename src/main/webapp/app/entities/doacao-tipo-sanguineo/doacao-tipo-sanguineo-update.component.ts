import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDoacaoTipoSanguineo, DoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';
import { DoacaoTipoSanguineoService } from './doacao-tipo-sanguineo.service';

@Component({
  selector: 'jhi-doacao-tipo-sanguineo-update',
  templateUrl: './doacao-tipo-sanguineo-update.component.html',
})
export class DoacaoTipoSanguineoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    podeDoarPara: [],
  });

  constructor(
    protected doacaoTipoSanguineoService: DoacaoTipoSanguineoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ doacaoTipoSanguineo }) => {
      this.updateForm(doacaoTipoSanguineo);
    });
  }

  updateForm(doacaoTipoSanguineo: IDoacaoTipoSanguineo): void {
    this.editForm.patchValue({
      id: doacaoTipoSanguineo.id,
      podeDoarPara: doacaoTipoSanguineo.podeDoarPara,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const doacaoTipoSanguineo = this.createFromForm();
    if (doacaoTipoSanguineo.id !== undefined) {
      this.subscribeToSaveResponse(this.doacaoTipoSanguineoService.update(doacaoTipoSanguineo));
    } else {
      this.subscribeToSaveResponse(this.doacaoTipoSanguineoService.create(doacaoTipoSanguineo));
    }
  }

  private createFromForm(): IDoacaoTipoSanguineo {
    return {
      ...new DoacaoTipoSanguineo(),
      id: this.editForm.get(['id'])!.value,
      podeDoarPara: this.editForm.get(['podeDoarPara'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDoacaoTipoSanguineo>>): void {
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
