package com.vmbb.mantenimiento.data.local.database.mantenimiento

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vmbb.mantenimiento.data.local.database.equipo.Equipo

@Entity(
    foreignKeys = [ForeignKey(
        entity = Equipo::class,
        parentColumns = ["serial"],
        childColumns = ["equipoSerial"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Mantenimiento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val equipoSerial: String,
    val fecha: String,
    val tecnicos: List<String>,
    val tipo: String, // "correctivo", "preventivo", etc.
    val estado: String // "Agendado", "Cancelado", etc.
)