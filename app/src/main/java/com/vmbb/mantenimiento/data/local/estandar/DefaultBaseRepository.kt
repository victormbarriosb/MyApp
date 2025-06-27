package com.vmbb.mantenimiento.data.local.estandar

import kotlinx.coroutines.flow.Flow

abstract class DefaultBaseRepository<T>(
    private val dao: BaseDao<T>,
    private val getAll: () -> Flow<List<T>>
) : BaseRepository<T> {

    override val items: Flow<List<T>> get() = getAll()

    override suspend fun insert(item: T) = dao.insertar(item)

    override suspend fun update(item: T) = dao.actualizar(item)

    override suspend fun delete(item: T) = dao.eliminar(item)
}