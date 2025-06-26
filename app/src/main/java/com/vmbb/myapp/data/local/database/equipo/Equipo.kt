package com.vmbb.myapp.data.local.database.equipo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Equipo(
    @PrimaryKey val serial: String,
    val modelo: String,
    val marca: String,
    val contrato: String, // "venta", "comodato", "cesion de uso"
    val laboratorio: String,
    val estado: String // "operativo", "fuera de servicio", etc.
)