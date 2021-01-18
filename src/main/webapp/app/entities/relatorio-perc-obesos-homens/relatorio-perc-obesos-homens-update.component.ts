import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRelatorioPercObesosHomens, RelatorioPercObesosHomens } from 'app/shared/model/relatorio-perc-obesos-homens.model';
import { RelatorioPercObesosHomensService } from './relatorio-perc-obesos-homens.service';

@Component({
  selector: 'jhi-relatorio-perc-obesos-homens-update',
  templateUrl: './relatorio-perc-obesos-homens-update.component.html',
})
export class RelatorioPercObesosHomensUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    percentual: [],
  });

  constructor(
    protected relatorioPercObesosHomensService: RelatorioPercObesosHomensService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relatorioPercObesosHomens }) => {
      this.updateForm(relatorioPercObesosHomens);
    });
  }

  updateForm(relatorioPercObesosHomens: IRelatorioPercObesosHomens): void {
    this.editForm.patchValue({
      id: relatorioPercObesosHomens.id,
      percentual: relatorioPercObesosHomens.percentual,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const relatorioPercObesosHomens = this.createFromForm();
    if (relatorioPercObesosHomens.id !== undefined) {
      this.subscribeToSaveResponse(this.relatorioPercObesosHomensService.update(relatorioPercObesosHomens));
    } else {
      this.subscribeToSaveResponse(this.relatorioPercObesosHomensService.create(relatorioPercObesosHomens));
    }
  }

  private createFromForm(): IRelatorioPercObesosHomens {
    return {
      ...new RelatorioPercObesosHomens(),
      id: this.editForm.get(['id'])!.value,
      percentual: this.editForm.get(['percentual'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRelatorioPercObesosHomens>>): void {
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
