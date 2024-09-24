package cl.bootcamp.calculadoraimc.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cl.bootcamp.calculadoraimc.components.ConfirmButtonAPD
import cl.bootcamp.calculadoraimc.components.DismissButton
import cl.bootcamp.calculadoraimc.model.Paciente

@Composable
fun AgregarPacienteDialog(onDismiss: () -> Unit, onAgregar: (Paciente) -> Unit) {
    var nombre by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Agregar Nuevo Paciente") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            ConfirmButtonAPD(onClick = {
                if (nombre.isNotEmpty()) {
                    val paciente = Paciente(nombre = nombre)
                    onAgregar(paciente)
                }
            })


        },
        dismissButton = {
            DismissButton(onClick = { onDismiss() })
        }
    )
}









