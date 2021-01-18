import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRelatorioPercObesosMulheres, RelatorioPercObesosMulheres } from 'app/shared/model/relatorio-perc-obesos-mulheres.model';
import { RelatorioPercObesosMulheresService } from './relatorio-perc-obesos-mulheres.service';

@Component({
  selector: 'jhi-relatorio-perc-obesos-mulheres-update',
  templateUrl: './relatorio-perc-obesos-mulheres-update.component.html',
})
export class RelatorioPercObesosMulheresUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    percentual: [],
  });

  constructor(
    protected relatorioPercObesosMulheresService: RelatorioPercObesosMulheresService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relatorioPercObesosMulheres }) => {
      this.updateForm(relatorioPercObesosMulheres);
    });
  }

  updateForm(relatorioPercObesosMulheres: IRelatorioPercObesosMulheres): void {
    this.editForm.patchValue({
      id: relatorioPercObesosMulheres.id,
      percentual: relatorioPercObesosMulheres.percentual,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const relatorioPercObesosMulheres = this.createFromForm();
    if (relatorioPercObesosMulheres.id !== undefined) {
      this.subscribeToSaveResponse(this.relatorioPercObesosMulheresService.update(relatorioPercObesosMulheres));
    } else {
      this.subscribeToSaveResponse(this.relatorioPercObesosMulheresService.create(relatorioPercObesosMulheres));
    }
  }

  private createFromForm(): IRelatorioPercObesosMulheres {
    return {
      ...new RelatorioPercObesosMulheres(),
      id: this.editForm.get(['id'])!.value,
      percentual: this.editForm.get(['percentual'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRelatorioPercObesosMulheres>>): void {
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
