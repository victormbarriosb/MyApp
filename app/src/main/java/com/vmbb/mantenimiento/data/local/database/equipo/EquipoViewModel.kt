package com.vmbb.mantenimiento.data.local.database.equipo

import com.vmbb.mantenimiento.data.local.estandar.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EquipoViewModel @Inject constructor(
    repository: EquipoRepository
) : BaseViewModel<Equipo>(repository) {
    // Podés agregar funciones extra si querés, como búsquedas por serial
}