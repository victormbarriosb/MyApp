package com.vmbb.mantenimiento.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vmbb.mantenimiento.R
import com.vmbb.mantenimiento.ui.elements.IconTextRowCustomIcon
import com.vmbb.mantenimiento.ui.equipos.EquiposViewModel

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
            TopAppBar(title = {
                IconTextRowCustomIcon( painterResource(id = R.drawable.machine),"Equipos")
            }
            )
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
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconTextRowCustomIcon( painterResource(id = R.drawable.company),equipo.marca)
                            IconTextRowCustomIcon( painterResource(id = R.drawable.model),equipo.modelo)
                        }
                    },
                    supportingContent = {
                        IconTextRowCustomIcon( painterResource(id = R.drawable.status),equipo.estado)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEquipoClick(equipo.serial) }
                        .padding(2.dp)
                )
                Divider()
            }
        }
    }
}


