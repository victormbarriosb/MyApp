package com.vmbb.mantenimiento.data.local.estandar

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    val items: Flow<List<T>>

    suspend fun insert(item: T)

    suspend fun update(item: T)

    suspend fun delete(item: T)
}