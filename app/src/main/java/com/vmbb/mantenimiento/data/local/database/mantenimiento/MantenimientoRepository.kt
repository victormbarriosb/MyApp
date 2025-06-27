package com.vmbb.mantenimiento.data.local.database.mantenimiento

import com.vmbb.mantenimiento.data.local.estandar.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class MantenimientoRepository @Inject constructor(
    private val dao: MantenimientoDao
) : BaseRepository<Mantenimiento> {

    // Opcional: usar una lista general si lo necesitás (por ejemplo, todos los mantenimientos sin filtrar)
    override val items: Flow<List<Mantenimiento>> = emptyFlow()

    override suspend fun insert(item: Mantenimiento) = dao.insertar(item)

    override suspend fun update(item: Mantenimiento) = dao.actualizar(item)

    override suspend fun delete(item: Mantenimiento) = dao.eliminar(item)

    // Extra: funciones específicas del dominio
    suspend fun getByEquipo(serial: String): List<Mantenimiento> =
        dao.getByEquipo(serial)

    fun getFlowPorEquipo(serial: String): Flow<List<Mantenimiento>> =
        dao.getPorEquipo(serial)

    suspend fun getPorId(id: Int): Mantenimiento? =
        dao.getPorId(id)
}