package com.vmbb.mantenimiento.ui.equipos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmbb.mantenimiento.data.local.database.equipo.Equipo
import com.vmbb.mantenimiento.data.local.database.equipo.EquipoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquiposViewModel @Inject constructor(
    private val dao: EquipoDao
) : ViewModel() {
    val equipos = dao.getAllFlow().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    fun insertarEquipo(equipo: Equipo) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertar(equipo)
        }
    }
    suspend fun getEquipoBySerialSuspend(serial: String): Equipo? {
        return dao.getBySerial(serial)
    }

    fun actualizarEquipo(equipo: Equipo) = viewModelScope.launch {
        dao.actualizar(equipo)
    }

}