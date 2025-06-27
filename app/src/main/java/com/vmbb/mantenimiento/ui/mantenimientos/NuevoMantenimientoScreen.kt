package com.vmbb.mantenimiento.ui.mantenimientos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vmbb.mantenimiento.data.local.database.mantenimiento.Mantenimiento
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NuevoMantenimientoScreen(
    serial: String,
    navController: NavController,
    viewModel: DetalleEquipoViewModel = hiltViewModel()
) {
    var fecha by remember { mutableStateOf(LocalDate.now().toString()) }
    var tecnicos by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("Correctivo") }
    var estado by remember { mutableStateOf("Agendado") }

    val tipos = listOf("Correctivo", "Preventivo", "Instalacion", "Verificacion", "Retiro", "Diagnostico")
    val estados = listOf("Agendado", "Cancelado", "Realizado", "En proceso")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Nuevo mantenimiento para $serial", style = MaterialTheme.typography.titleMedium)

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
                val mantenimiento = Mantenimiento(
                    equipoSerial = serial,
                    fecha = fecha,
                    tecnicos = tecnicos.split(",").map { it.trim() },
                    tipo = tipo,
                    estado = estado
                )
                viewModel.insertar(mantenimiento)
                navController.popBackStack()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Guardar")
        }
    }
}

@Composable
fun DropdownMenuField(
    label: String,
    selected: String,
    items: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {

        OutlinedTextField(
            value = selected,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }, // este funciona si NO hay interacción de foco
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    Modifier.clickable { expanded = true }
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}