package com.vmbb.mantenimiento.data.local.database.mantenimiento

import androidx.room.Dao
import androidx.room.Query
import com.vmbb.mantenimiento.data.local.estandar.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface MantenimientoDao : BaseDao<Mantenimiento> {
    @Query("SELECT * FROM Mantenimiento WHERE equipoSerial = :serial")
    suspend fun getByEquipo(serial: String): List<Mantenimiento>

    @Query("SELECT * FROM Mantenimiento WHERE equipoSerial = :serial ORDER BY fecha DESC")
    fun getPorEquipo(serial: String): Flow<List<Mantenimiento>>

    @Query("SELECT * FROM Mantenimiento WHERE id = :id")
    suspend fun getPorId(id: Int): Mantenimiento?
}