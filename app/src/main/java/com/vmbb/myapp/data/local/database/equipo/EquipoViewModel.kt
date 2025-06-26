package com.vmbb.myapp.data.local.database.equipo

import com.vmbb.myapp.data.local.estandar.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EquipoViewModel @Inject constructor(
    repository: EquipoRepository
) : BaseViewModel<Equipo>(repository) {
    // Podés agregar funciones extra si querés, como búsquedas por serial
}