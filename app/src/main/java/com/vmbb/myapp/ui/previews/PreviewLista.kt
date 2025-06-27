package com.vmbb.myapp.ui.previews

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vmbb.myapp.data.local.database.equipo.Equipo


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewListaEquiposScreen() {
    val equipos = listOf(
        Equipo(
            serial = "12345A",
            modelo = "ModeloX",
            marca = "MarcaY",
            contrato = "venta",
            laboratorio = "Lab1",
            estado = "operativo"
        ),
        Equipo(
            serial = "67890B",
            modelo = "ModeloZ",
            marca = "MarcaW",
            contrato = "comodato",
            laboratorio = "Lab2",
            estado = "fuera de servicio"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Equipos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Acción dummy */ }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar equipo")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(equipos) { equipo ->
                ListItem(
                    headlineContent = { Text("${equipo.marca} ${equipo.modelo}") },
                    supportingContent = { Text("Estado: ${equipo.estado}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Acción dummy */ }
                        .padding(8.dp)
                )
                Divider()
            }
        }
    }
}