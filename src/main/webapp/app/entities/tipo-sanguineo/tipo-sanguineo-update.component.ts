import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITipoSanguineo, TipoSanguineo } from 'app/shared/model/tipo-sanguineo.model';
import { TipoSanguineoService } from './tipo-sanguineo.service';
import { IDoacaoTipoSanguineo } from 'app/shared/model/doacao-tipo-sanguineo.model';
import { DoacaoTipoSanguineoService } from 'app/entities/doacao-tipo-sanguineo/doacao-tipo-sanguineo.service';
import { IRecepcaoTipoSanguineo } from 'app/shared/model/recepcao-tipo-sanguineo.model';
import { RecepcaoTipoSanguineoService } from 'app/entities/recepcao-tipo-sanguineo/recepcao-tipo-sanguineo.service';

type SelectableEntity = IDoacaoTipoSanguineo | IRecepcaoTipoSanguineo;

@Component({
  selector: 'jhi-tipo-sanguineo-update',
  templateUrl: './tipo-sanguineo-update.component.html',
})
export class TipoSanguineoUpdateComponent implements OnInit {
  isSaving = false;
  doacaotiposanguineos: IDoacaoTipoSanguineo[] = [];
  recepcaotiposanguineos: IRecepcaoTipoSanguineo[] = [];

  editForm = this.fb.group({
    id: [],
    descricao: [],
    doacaoTipoSanguineoId: [],
    recepcaoTipoSanguineoId: [],
  });

  constructor(
    protected tipoSanguineoService: TipoSanguineoService,
    protected doacaoTipoSanguineoService: DoacaoTipoSanguineoService,
    protected recepcaoTipoSanguineoService: RecepcaoTipoSanguineoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoSanguineo }) => {
      this.updateForm(tipoSanguineo);

      this.doacaoTipoSanguineoService
        .query()
        .subscribe((res: HttpResponse<IDoacaoTipoSanguineo[]>) => (this.doacaotiposanguineos = res.body || []));

      this.recepcaoTipoSanguineoService
        .query()
        .subscribe((res: HttpResponse<IRecepcaoTipoSanguineo[]>) => (this.recepcaotiposanguineos = res.body || []));
    });
  }

  updateForm(tipoSanguineo: ITipoSanguineo): void {
    this.editForm.patchValue({
      id: tipoSanguineo.id,
      descricao: tipoSanguineo.descricao,
      doacaoTipoSanguineoId: tipoSanguineo.doacaoTipoSanguineoId,
      recepcaoTipoSanguineoId: tipoSanguineo.recepcaoTipoSanguineoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tipoSanguineo = this.createFromForm();
    if (tipoSanguineo.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoSanguineoService.update(tipoSanguineo));
    } else {
      this.subscribeToSaveResponse(this.tipoSanguineoService.create(tipoSanguineo));
    }
  }

  private createFromForm(): ITipoSanguineo {
    return {
      ...new TipoSanguineo(),
      id: this.editForm.get(['id'])!.value,
      descricao: this.editForm.get(['descricao'])!.value,
      doacaoTipoSanguineoId: this.editForm.get(['doacaoTipoSanguineoId'])!.value,
      recepcaoTipoSanguineoId: this.editForm.get(['recepcaoTipoSanguineoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoSanguineo>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
