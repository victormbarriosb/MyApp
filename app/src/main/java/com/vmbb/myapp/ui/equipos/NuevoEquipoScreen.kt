package com.vmbb.myapp.ui.equipos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vmbb.myapp.data.local.database.Equipo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoEquipoScreen(
    viewModel: EquiposViewModel = hiltViewModel(),
    navController: NavController,
    onGuardar: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var modelo by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var serial by remember { mutableStateOf("") }
    var contrato by remember { mutableStateOf("Venta") }
    var laboratorio by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("Operativo") }

    val contratos = listOf("Venta", "Comodato", "Cesión de uso")
    val estados = listOf("Operativo", "Fuera de servicio", "Mantenimiento", "Semi operativo")

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Nuevo Equipo") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.insertarEquipo(
                    Equipo(
                        serial = serial,
                        modelo = modelo,
                        marca = marca,
                        contrato = contrato,
                        laboratorio = laboratorio,
                        estado = estado
                    )
                )
                navController.popBackStack() // 👈 vuelve atrás
            }) {
                Icon(Icons.Default.Done, contentDescription = "Guardar")
            }
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(value = serial, onValueChange = { serial = it }, label = { Text("Serial") })
            OutlinedTextField(value = modelo, onValueChange = { modelo = it }, label = { Text("Modelo") })
            OutlinedTextField(value = marca, onValueChange = { marca = it }, label = { Text("Marca") })
            OutlinedTextField(value = laboratorio, onValueChange = { laboratorio = it }, label = { Text("Laboratorio") })

            Spacer(modifier = Modifier.height(8.dp))

            Text("Tipo de contrato")
            contratos.forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = contrato == it, onClick = { contrato = it })
                    Text(it)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

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
