package com.vmbb.myapp.ui.equipos

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vmbb.myapp.data.local.database.Equipo

@Composable
fun ListaEquipos(equipos: List<Equipo>) {
    LazyColumn {
        items(equipos) { equipo ->
            Text(text = "${equipo.marca} ${equipo.modelo} - ${equipo.estado}")
        }
    }
}
