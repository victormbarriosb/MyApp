package com.vmbb.myapp.ui.mantenimientos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmbb.myapp.data.local.database.equipo.Equipo
import com.vmbb.myapp.data.local.database.equipo.EquipoDao
import com.vmbb.myapp.data.local.database.mantenimiento.Mantenimiento
import com.vmbb.myapp.data.local.database.mantenimiento.MantenimientoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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