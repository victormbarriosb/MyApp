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

package com.vmbb.mantenimiento.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.vmbb.mantenimiento.data.local.database.AppDatabase
import com.vmbb.mantenimiento.data.local.database.DataItemTypeDao
import com.vmbb.mantenimiento.data.local.database.equipo.EquipoDao
import com.vmbb.mantenimiento.data.local.database.mantenimiento.MantenimientoDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "mantenimiento_db"
        ).build()
    }

    @Provides
    fun provideEquipoDao(db: AppDatabase): EquipoDao = db.equipoDao()

    @Provides
    fun provideMantenimientoDao(db: AppDatabase): MantenimientoDao = db.mantenimientoDao()

    @Provides
    fun provideDataItemTypeDao(db: AppDatabase): DataItemTypeDao = db.dataItemTypeDao()
}