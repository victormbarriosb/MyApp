package com.vmbb.myapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vmbb.myapp.ui.equipos.EquiposViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaEquiposScreen(
    viewModel: EquiposViewModel = hiltViewModel(),
    onEquipoClick: (String) -> Unit,
    onAddEquipo: () -> Unit
) {
    val equipos by viewModel.equipos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Equipos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddEquipo) {
                Icon(Icons.Default.Add, contentDescription = "Agregar equipo")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(equipos) { equipo ->
                ListItem(
                    headlineContent = {
                        Text("${equipo.marca} ${equipo.modelo}")
                    },
                    supportingContent = {
                        Text("Estado: ${equipo.estado}")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEquipoClick(equipo.serial) }
                        .padding(8.dp)
                )
                Divider()
            }
        }
    }
}


