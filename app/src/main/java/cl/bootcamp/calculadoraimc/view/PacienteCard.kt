package cl.bootcamp.calculadoraimc.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.bootcamp.calculadoraimc.components.CardButton
import cl.bootcamp.calculadoraimc.model.Paciente

@Composable
fun PacienteCard(paciente: Paciente, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Nombre: ${paciente.nombre}")

                // Mostrar los demás datos solo si no están vacíos
                if (paciente.edad.isNotEmpty()) {
                    Text(text = "Edad: ${paciente.edad}")
                    Spacer(modifier = Modifier.height(4.dp))
                }
                if (paciente.sexo.isNotEmpty()) {
                    Text(text = "Género: ${paciente.sexo}")
                    Spacer(modifier = Modifier.height(4.dp))
                }
                if (paciente.imcCalculado) {
                    Text (text = paciente.imc)
                    Spacer(modifier = Modifier.height(4.dp))
                }
                if (paciente.estadoSalud.isNotEmpty()) {
                    Text(text = "Estado de salud: ${paciente.estadoSalud}")
                }
            }

            // Mostrar el botón de calcular solo si el IMC no ha sido calculado
            if (!paciente.imcCalculado) {
                CardButton(onClick = {
                    navController.navigate("calculadoraIMC/${paciente.nombre}")
                })
            }
        }
    }
}


