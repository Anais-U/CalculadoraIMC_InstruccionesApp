package cl.bootcamp.calculadoraimc.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.bootcamp.calculadoraimc.components.CustomFloatingActionButton
import cl.bootcamp.calculadoraimc.viewmodel.PacienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPacientes(navController: NavHostController, viewModel: PacienteViewModel) {
    val pacientes by viewModel.listaPacientes

    // Estado para controlar el diálogo
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Pacientes", color = Color.White) },
                colors = topAppBarColors(
                    containerColor = Color(0xFFB388FF),  // Fondo morado
                    titleContentColor = Color.White,      // Texto blanco

                )
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton(
                onClick = {
                    showDialog = true
                }
            )


        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    items(pacientes, key = { paciente -> paciente.id }) { paciente ->
                        PacienteCard(paciente, navController)  // Pasa navController a PacienteCard

                    }
                }
            }

        }
    )

    // Mostrar el diálogo para agregar paciente
    if (showDialog) {
        AgregarPacienteDialog(viewModel = viewModel, onDismiss = { showDialog = false })
    }
}

