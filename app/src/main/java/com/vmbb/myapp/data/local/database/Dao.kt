package com.vmbb.myapp.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*@Dao
interface EquipoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(equipo: Equipo)

    @Query("SELECT * FROM Equipo")
    suspend fun getAll(): List<Equipo>

    @Query("SELECT * FROM Equipo")
    fun getAllFlow(): Flow<List<Equipo>>

    @Query("SELECT * FROM Equipo WHERE serial = :serial LIMIT 1")
    suspend fun getBySerial(serial: String): Equipo?

    @Update
    suspend fun update(equipo: Equipo)
}*/
/*@Dao
interface MantenimientoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mantenimiento: Mantenimiento)

    @Query("SELECT * FROM Mantenimiento WHERE equipoSerial = :serial")
    suspend fun getByEquipo(serial: String): List<Mantenimiento>

    @Query("SELECT * FROM Mantenimiento WHERE equipoSerial = :serial ORDER BY fecha DESC")
    fun getPorEquipo(serial: String): Flow<List<Mantenimiento>>

    @Query("SELECT * FROM Mantenimiento WHERE id = :id")
    suspend fun getPorId(id: Int): Mantenimiento?

    @Insert
    suspend fun insertar(m: Mantenimiento)

    @Update
    suspend fun actualizar(m: Mantenimiento)

    @Delete
    suspend fun eliminar(m: Mantenimiento)
}*/
/*@Dao
interface RepuestoDao {
    @Query("SELECT * FROM Repuesto WHERE mantenimientoId = :mantenimientoId")
    suspend fun getByMantenimiento(mantenimientoId: Int): List<Repuesto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repuesto: Repuesto)

    @Delete
    suspend fun delete(repuesto: Repuesto)
}*/
