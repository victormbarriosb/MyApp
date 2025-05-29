package com.vmbb.myapp.ui.equipos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmbb.myapp.data.local.database.Equipo
import com.vmbb.myapp.data.local.database.EquipoDao
import com.vmbb.myapp.data.local.database.Mantenimiento
import com.vmbb.myapp.data.local.database.MantenimientoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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
            dao.insert(equipo)
        }
    }
    suspend fun getEquipoBySerialSuspend(serial: String): Equipo? {
        return dao.getBySerial(serial)
    }

    fun actualizarEquipo(equipo: Equipo) = viewModelScope.launch {
        dao.update(equipo)
    }

}


@HiltViewModel
class DetalleEquipoViewModel @Inject constructor(
    private val equipoDao: EquipoDao,
    private val mantenimientoDao: MantenimientoDao
) : ViewModel() {
    private val _equipo = MutableStateFlow<Equipo?>(null)
    val equipo: StateFlow<Equipo?> = _equipo

    val mantenimientos = MutableStateFlow<List<Mantenimiento>>(emptyList())

    fun loadEquipo(serial: String) {
        viewModelScope.launch {
            _equipo.value = equipoDao.getBySerial(serial)
            mantenimientos.value = mantenimientoDao.getByEquipo(serial)
        }
    }

    fun getMantenimientos(serial: String): Flow<List<Mantenimiento>> =
        mantenimientoDao.getPorEquipo(serial)

    suspend fun getMantenimientoPorId(id: Int): Mantenimiento? =
        mantenimientoDao.getPorId(id)

    fun insertar(m: Mantenimiento) = viewModelScope.launch {
        mantenimientoDao.insertar(m)
    }

    fun actualizar(m: Mantenimiento) = viewModelScope.launch {
        mantenimientoDao.actualizar(m)
    }

    fun eliminar(m: Mantenimiento) = viewModelScope.launch {
        mantenimientoDao.eliminar(m)
    }



}