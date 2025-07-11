/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmbb.mantenimiento.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vmbb.mantenimiento.data.local.database.equipo.Equipo
import com.vmbb.mantenimiento.data.local.database.equipo.EquipoDao
import com.vmbb.mantenimiento.data.local.database.mantenimiento.Mantenimiento
import com.vmbb.mantenimiento.data.local.database.mantenimiento.MantenimientoDao

@Database(
    entities = [Equipo::class,
        Mantenimiento::class,
        DataItemType::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun equipoDao(): EquipoDao
    abstract fun mantenimientoDao(): MantenimientoDao
    abstract fun dataItemTypeDao(): DataItemTypeDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mantenimiento_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}