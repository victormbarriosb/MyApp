package com.vmbb.mantenimiento.data.local.database

/*@Entity
data class Equipo(
    @PrimaryKey val serial: String,
    val modelo: String,
    val marca: String,
    val contrato: String, // "venta", "comodato", "cesion de uso"
    val laboratorio: String,
    val estado: String // "operativo", "fuera de servicio", etc.
)*/
/*@Entity(
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
)*/
/*
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

*/
