package com.vmbb.myapp.data.local.database.mantenimiento

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vmbb.myapp.data.local.database.equipo.Equipo
import java.time.LocalDate

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
    val fecha: LocalDate,
    val tecnicos: List<String>,
    val tipo: String, // "correctivo", "preventivo", etc.
    val estado: String // "Agendado", "Cancelado", etc.
)