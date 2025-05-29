package com.vmbb.myapp.ui.equipos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vmbb.myapp.data.local.database.Mantenimiento

@Composable
fun EditarMantenimientoScreen(
    mantenimientoId: Int,
    navController: NavController,
    viewModel: DetalleEquipoViewModel = hiltViewModel()
) {
    // Estado para cargar mantenimiento
    var mantenimiento by remember { mutableStateOf<Mantenimiento?>(null) }

    // Cargar datos al inicio
    LaunchedEffect(mantenimientoId) {
        mantenimiento = viewModel.getMantenimientoPorId(mantenimientoId)
    }

    if (mantenimiento == null) {
        // Mostrar loading o mensaje mientras carga
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        var fecha by remember { mutableStateOf(mantenimiento!!.fecha) }
        var tecnicos by remember { mutableStateOf(mantenimiento!!.tecnicos.joinToString(", ")) }
        var tipo by remember { mutableStateOf(mantenimiento!!.tipo) }
        var estado by remember { mutableStateOf(mantenimiento!!.estado) }

        val tipos = listOf("correctivo", "preventivo", "instalacion", "verificacion", "retiro", "diagnostico")
        val estados = listOf("Agendado", "Cancelado", "Realizado", "En proceso")

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Text("Editar mantenimiento", style = MaterialTheme.typography.titleMedium)

            OutlinedTextField(
                value = tecnicos,
                onValueChange = { tecnicos = it },
                label = { Text("Técnicos (separados por coma)") },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenuField("Tipo", tipo, tipos) { tipo = it }
            DropdownMenuField("Estado", estado, estados) { estado = it }

            Button(
                onClick = {
                    val actualizado = mantenimiento!!.copy(
                        fecha = fecha,
                        tecnicos = tecnicos.split(",").map { it.trim() },
                        tipo = tipo,
                        estado = estado
                    )
                    viewModel.actualizar(actualizado)
                    navController.popBackStack()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Guardar cambios")
            }
        }
    }
}
