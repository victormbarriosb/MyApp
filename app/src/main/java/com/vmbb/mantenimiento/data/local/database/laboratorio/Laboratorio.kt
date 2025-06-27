package com.vmbb.mantenimiento.data.local.database.laboratorio

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Laboratorio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val utmx: Double,
    val utmy: Double,
    val correo: String
)