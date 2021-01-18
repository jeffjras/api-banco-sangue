import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import {
  IRelatorioQtdeDoadoresParaCadaTipoReceptor,
  RelatorioQtdeDoadoresParaCadaTipoReceptor,
} from 'app/shared/model/relatorio-qtde-doadores-para-cada-tipo-receptor.model';
import { RelatorioQtdeDoadoresParaCadaTipoReceptorService } from './relatorio-qtde-doadores-para-cada-tipo-receptor.service';

@Component({
  selector: 'jhi-relatorio-qtde-doadores-para-cada-tipo-receptor-update',
  templateUrl: './relatorio-qtde-doadores-para-cada-tipo-receptor-update.component.html',
})
export class RelatorioQtdeDoadoresParaCadaTipoReceptorUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    sangue: [],
    totalDoador: [],
  });

  constructor(
    protected relatorioQtdeDoadoresParaCadaTipoReceptorService: RelatorioQtdeDoadoresParaCadaTipoReceptorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relatorioQtdeDoadoresParaCadaTipoReceptor }) => {
      this.updateForm(relatorioQtdeDoadoresParaCadaTipoReceptor);
    });
  }

  updateForm(relatorioQtdeDoadoresParaCadaTipoReceptor: IRelatorioQtdeDoadoresParaCadaTipoReceptor): void {
    this.editForm.patchValue({
      id: relatorioQtdeDoadoresParaCadaTipoReceptor.id,
      sangue: relatorioQtdeDoadoresParaCadaTipoReceptor.sangue,
      totalDoador: relatorioQtdeDoadoresParaCadaTipoReceptor.totalDoador,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const relatorioQtdeDoadoresParaCadaTipoReceptor = this.createFromForm();
    if (relatorioQtdeDoadoresParaCadaTipoReceptor.id !== undefined) {
      this.subscribeToSaveResponse(this.relatorioQtdeDoadoresParaCadaTipoReceptorService.update(relatorioQtdeDoadoresParaCadaTipoReceptor));
    } else {
      this.subscribeToSaveResponse(this.relatorioQtdeDoadoresParaCadaTipoReceptorService.create(relatorioQtdeDoadoresParaCadaTipoReceptor));
    }
  }

  private createFromForm(): IRelatorioQtdeDoadoresParaCadaTipoReceptor {
    return {
      ...new RelatorioQtdeDoadoresParaCadaTipoReceptor(),
      id: this.editForm.get(['id'])!.value,
      sangue: this.editForm.get(['sangue'])!.value,
      totalDoador: this.editForm.get(['totalDoador'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRelatorioQtdeDoadoresParaCadaTipoReceptor>>): void {
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
