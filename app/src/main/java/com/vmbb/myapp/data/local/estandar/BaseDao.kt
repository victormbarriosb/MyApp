package com.vmbb.myapp.data.local.estandar

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(entity: T)

    @Update
    suspend fun actualizar(entity: T)

    @Delete
    suspend fun eliminar(entity: T)
}