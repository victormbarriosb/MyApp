package com.vmbb.mantenimiento.data.local.database.repuesto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vmbb.mantenimiento.data.local.database.mantenimiento.Mantenimiento

@Entity(
    foreignKeys = [ForeignKey(
        entity = Mantenimiento::class,
        parentColumns = ["id"],
        childColumns = ["mantenimientoId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Repuesto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mantenimientoId: Int,
    val nombre: String,
    val cantidad: Int
)

