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

package com.vmbb.mantenimiento.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vmbb.mantenimiento.ui.screens.DetalleEquipoScreen
import com.vmbb.mantenimiento.ui.equipos.EditarEquipoScreen
import com.vmbb.mantenimiento.ui.mantenimientos.EditarMantenimientoScreen
import com.vmbb.mantenimiento.ui.screens.ListaEquiposScreen
import com.vmbb.mantenimiento.ui.equipos.NuevoEquipoScreen
import com.vmbb.mantenimiento.ui.mantenimientos.NuevoMantenimientoScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "listaEquipos") {
        composable("listaEquipos") {
            ListaEquiposScreen(
                onEquipoClick = { serial ->
                    navController.navigate("detalleEquipo/$serial")
                },
                onAddEquipo = {
                    navController.navigate("nuevoEquipo")
                }
            )
        }
        composable("detalleEquipo/{serial}") { backStackEntry ->
            val serial = backStackEntry.arguments?.getString("serial")
            if (serial != null) {
                DetalleEquipoScreen(
                    serial = serial,
                    navController = navController
                )
            }
        }
        composable("nuevoEquipo") {
            NuevoEquipoScreen(navController = navController)
        }
        composable("editarEquipo/{serial}") { backStackEntry ->
            val serial = backStackEntry.arguments?.getString("serial") ?: return@composable
            EditarEquipoScreen(navController, serial)
        }
        composable("nuevoMantenimiento/{serial}") { backStackEntry ->
            val serial = backStackEntry.arguments?.getString("serial")!!
            NuevoMantenimientoScreen(serial, navController)
        }
        composable("editarMantenimiento/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            EditarMantenimientoScreen(id, navController)
        }
    }
}
