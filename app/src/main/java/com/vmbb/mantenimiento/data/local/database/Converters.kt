package com.vmbb.mantenimiento.data.local.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun fromList(value: List<String>): String = value.joinToString(",")

    @TypeConverter
    fun toList(value: String): List<String> = value.split(",")

    @TypeConverter
    fun fromDate(value: LocalDate): String = value.toString()

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toDate(value: String): LocalDate = LocalDate.parse(value)
}