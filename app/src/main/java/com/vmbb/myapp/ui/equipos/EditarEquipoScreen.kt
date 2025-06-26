package com.vmbb.myapp.ui.equipos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vmbb.myapp.data.local.database.equipo.Equipo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarEquipoScreen(
    navController: NavController,
    serial: String,
    viewModel: EquiposViewModel = hiltViewModel()
) {
    val equipo by produceState<Equipo?>(initialValue = null, serial) {
        value = viewModel.getEquipoBySerialSuspend(serial)
    }

    var modelo by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var contrato by remember { mutableStateOf("") }
    var laboratorio by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    LaunchedEffect(equipo) {
        equipo?.let {
            modelo = it.modelo
            marca = it.marca
            contrato = it.contrato
            laboratorio = it.laboratorio
            estado = it.estado
        }
    }

    val contratos = listOf("Venta", "Comodato", "Cesión de uso")
    val estados = listOf("Operativo", "Fuera de servicio", "Mantenimiento", "Semi operativo")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Editar Equipo") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (equipo != null) {
                    viewModel.actualizarEquipo(
                        equipo!!.copy(
                            modelo = modelo,
                            marca = marca,
                            contrato = contrato,
                            laboratorio = laboratorio,
                            estado = estado
                        )
                    )
                    navController.popBackStack()
                }
            }) {
                Icon(Icons.Default.Done, contentDescription = "Guardar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(value = modelo, onValueChange = { modelo = it }, label = { Text("Modelo") })
            OutlinedTextField(value = marca, onValueChange = { marca = it }, label = { Text("Marca") })
            OutlinedTextField(value = laboratorio, onValueChange = { laboratorio = it }, label = { Text("Laboratorio") })

            Text("Contrato")
            contratos.forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = contrato == it, onClick = { contrato = it })
                    Text(it)
                }
            }

            Text("Estado")
            estados.forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = estado == it, onClick = { estado = it })
                    Text(it)
                }
            }
        }
    }
}
