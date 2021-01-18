import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRelatorioQtdCandPorEstado, RelatorioQtdCandPorEstado } from 'app/shared/model/relatorio-qtd-cand-por-estado.model';
import { RelatorioQtdCandPorEstadoService } from './relatorio-qtd-cand-por-estado.service';

@Component({
  selector: 'jhi-relatorio-qtd-cand-por-estado-update',
  templateUrl: './relatorio-qtd-cand-por-estado-update.component.html',
})
export class RelatorioQtdCandPorEstadoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    qtde: [],
    estado: [],
  });

  constructor(
    protected relatorioQtdCandPorEstadoService: RelatorioQtdCandPorEstadoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relatorioQtdCandPorEstado }) => {
      this.updateForm(relatorioQtdCandPorEstado);
    });
  }

  updateForm(relatorioQtdCandPorEstado: IRelatorioQtdCandPorEstado): void {
    this.editForm.patchValue({
      id: relatorioQtdCandPorEstado.id,
      qtde: relatorioQtdCandPorEstado.qtde,
      estado: relatorioQtdCandPorEstado.estado,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const relatorioQtdCandPorEstado = this.createFromForm();
    if (relatorioQtdCandPorEstado.id !== undefined) {
      this.subscribeToSaveResponse(this.relatorioQtdCandPorEstadoService.update(relatorioQtdCandPorEstado));
    } else {
      this.subscribeToSaveResponse(this.relatorioQtdCandPorEstadoService.create(relatorioQtdCandPorEstado));
    }
  }

  private createFromForm(): IRelatorioQtdCandPorEstado {
    return {
      ...new RelatorioQtdCandPorEstado(),
      id: this.editForm.get(['id'])!.value,
      qtde: this.editForm.get(['qtde'])!.value,
      estado: this.editForm.get(['estado'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRelatorioQtdCandPorEstado>>): void {
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
