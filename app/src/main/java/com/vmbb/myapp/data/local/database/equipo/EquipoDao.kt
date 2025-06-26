package com.vmbb.myapp.data.local.database.equipo

import androidx.room.Dao
import androidx.room.Query
import com.vmbb.myapp.data.local.estandar.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipoDao : BaseDao<Equipo> {
    @Query("SELECT * FROM Equipo")
    suspend fun getAll(): List<Equipo>

    @Query("SELECT * FROM Equipo")
    fun getAllFlow(): Flow<List<Equipo>>

    @Query("SELECT * FROM Equipo WHERE serial = :serial LIMIT 1")
    suspend fun getBySerial(serial: String): Equipo?
}
