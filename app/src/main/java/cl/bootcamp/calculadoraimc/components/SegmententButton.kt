package cl.bootcamp.calculadoraimc.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MultiButtonSegmented(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray) // Fondo gris claro para todo el botón
            .padding(4.dp) // Espacio alrededor del contenido
            .height(50.dp), // Altura de los botones
        horizontalArrangement = Arrangement.SpaceBetween // Distribuye los botones equitativamente
    ) {
        options.forEach { option ->
            val isSelected = option == selectedOption
            Surface(
                modifier = Modifier
                    .weight(1f) // Asegura que los botones ocupen el mismo espacio
                    .fillMaxHeight()
                    .clickable { onOptionSelected(option) },
                color = if (isSelected) Color(0xFFB388FF) else Color.White,
                contentColor = if (isSelected) Color.White else Color.Black
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = option)

                    // Si la opción está seleccionada, mostramos un "check"
                    if (isSelected) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("✓") // Mostrar un "check" simple
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiButtonSegmentedPreview() {
    MultiButtonSegmented(
        options = listOf("Hombre", "Mujer"),
        selectedOption = "Hombre",
        onOptionSelected = {}
    )
}
