package com.vmbb.mantenimiento.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vmbb.mantenimiento.R
import com.vmbb.mantenimiento.ui.elements.IconTextRowCustomIcon
import com.vmbb.mantenimiento.ui.mantenimientos.DetalleEquipoViewModel

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconTextRowCustomIcon( painterResource(id = R.drawable.company),it.marca)
                IconTextRowCustomIcon( painterResource(id = R.drawable.model),it.modelo)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                IconTextRowCustomIcon( painterResource(id = R.drawable.lab),it.laboratorio)
                IconTextRowCustomIcon( painterResource(id = R.drawable.contract),it.contrato)
            }
            Spacer(modifier = Modifier.height(5.dp))
            IconTextRowCustomIcon( painterResource(id = R.drawable.status),it.estado)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navController.navigate("editarEquipo/${equipo?.serial}")
        }) {
            Text("Editar")
        }
        Spacer(modifier = Modifier.height(24.dp))

        val mantenimientos by viewModel.getMantenimientos(serial).collectAsState(emptyList())

        /*Text("Mantenimientos", style = MaterialTheme.typography.titleMedium)
        mantenimientos.forEach { m ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { *//* ir a editar *//* }
            ) {
                Column(Modifier.padding(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        IconTextRowCustomIcon( painterResource(id = R.drawable.gear), m.tipo)
                        IconTextRowCustomIcon( painterResource(id = R.drawable.status), m.estado)
                        IconTextRowCustomIcon( painterResource(id = R.drawable.date), m.fecha)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    m.tecnicos.forEach { tecnico ->
                        Spacer(modifier = Modifier.height(5.dp))
                        IconTextRowCustomIcon( painterResource(id = R.drawable.technician), tecnico)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End, // fuerza alineación a la izquierda
                        verticalAlignment = Alignment.CenterVertically
                    )  {
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
        }*/

        val tabs = listOf("Mantenimientos", "Respuestos")
        var selectedTabIndex by remember { mutableStateOf(0) }

        Column {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index }
                    )
                }
            }

            when (selectedTabIndex) {
                1 -> {
                    Text("Modelo: ${equipo?.modelo}", style = MaterialTheme.typography.bodyLarge)
                    Text("Marca: ${equipo?.marca}")
                    // etc.
                }

                0 -> {
                    mantenimientos.forEach { m ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { /* ir a editar */ }
                        ) {
                            Column(Modifier.padding(8.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    IconTextRowCustomIcon(painterResource(id = R.drawable.gear), m.tipo)
                                    IconTextRowCustomIcon(painterResource(id = R.drawable.status), m.estado)
                                    IconTextRowCustomIcon(painterResource(id = R.drawable.date), m.fecha)
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                m.tecnicos.forEach { tecnico ->
                                    Spacer(modifier = Modifier.height(5.dp))
                                    IconTextRowCustomIcon(
                                        painterResource(id = R.drawable.technician),
                                        tecnico
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
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
        }
    }
}
