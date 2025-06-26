package com.vmbb.myapp.data.local.database.repuesto

import com.vmbb.myapp.data.local.estandar.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class RepuestoRepository @Inject constructor(
    private val dao: RepuestoDao
) : BaseRepository<Repuesto> {

    override val items: Flow<List<Repuesto>> = emptyFlow() // No tiene "getAll"

    override suspend fun insert(item: Repuesto) = dao.insertar(item)

    override suspend fun update(item: Repuesto) = dao.actualizar(item)

    override suspend fun delete(item: Repuesto) = dao.eliminar(item)

    // Extra: obtener repuestos por mantenimiento
    suspend fun getByMantenimiento(mantenimientoId: Int): List<Repuesto> =
        dao.getByMantenimiento(mantenimientoId)
}