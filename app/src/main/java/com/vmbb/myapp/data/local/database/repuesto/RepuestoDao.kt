package com.vmbb.myapp.data.local.database.repuesto

import androidx.room.Dao
import androidx.room.Query
import com.vmbb.myapp.data.local.estandar.BaseDao

@Dao
interface RepuestoDao : BaseDao<Repuesto> {
    @Query("SELECT * FROM Repuesto WHERE mantenimientoId = :mantenimientoId")
    suspend fun getByMantenimiento(mantenimientoId: Int): List<Repuesto>
}