package cl.bootcamp.calculadoraimc.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Nombre: ${paciente.nombre}")
            CardButton(onClick = {
                navController.navigate("calculadoraIMC/${paciente.nombre}")
            })

            }
        }
    }

