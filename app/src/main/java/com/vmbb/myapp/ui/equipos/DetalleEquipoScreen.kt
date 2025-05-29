package com.vmbb.myapp.ui.equipos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DetalleEquipoScreen(
    serial: String,
    viewModel: DetalleEquipoViewModel = hiltViewModel(),
    navController: NavController
) {
    val equipo by viewModel.equipo.collectAsState()
    val mantenimientos by viewModel.mantenimientos.collectAsState()

    LaunchedEffect(serial) {
        viewModel.loadEquipo(serial)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        equipo?.let {
            Text("Modelo: ${it.modelo}")
            Text("Marca: ${it.marca}")
            Text("Contrato: ${it.contrato}")
            Text("Laboratorio: ${it.laboratorio}")
            Text("Estado: ${it.estado}")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navController.navigate("editarEquipo/${equipo?.serial}")
        }) {
            Text("Editar")
        }
        Spacer(modifier = Modifier.height(24.dp))
        /*Text("Mantenimientos", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(mantenimientos) { m ->
                ListItem(
                    headlineContent = {
                        Text("${m.tipo} - ${m.estado}")
                    },
                    supportingContent = {
                        Text("Fecha: ${m.fecha}")
                    }
                )
                Divider()
            }
        }*/

        val mantenimientos by viewModel.getMantenimientos(serial).collectAsState(emptyList())

        Text("Mantenimientos", style = MaterialTheme.typography.titleMedium)

        mantenimientos.forEach { m ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { /* ir a editar */ }
            ) {
                Column(Modifier.padding(8.dp)) {
                    Text("Fecha: ${m.fecha}")
                    Text("Tipo: ${m.tipo}")
                    Text("Estado: ${m.estado}")
                    Text("Técnicos: ${m.tecnicos}")
                    Row {
                        IconButton(onClick = { viewModel.eliminar(m) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                        IconButton(onClick = {
                            navController.navigate("editarMantenimiento/${m.id}")
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar mantenimiento")
                        }
                    }
                }
            }
        }
        FloatingActionButton(onClick = {
            navController.navigate("nuevoMantenimiento/$serial")
        }) {
            Icon(Icons.Default.Add, contentDescription = "Nuevo Mantenimiento")
        }

    }
}
