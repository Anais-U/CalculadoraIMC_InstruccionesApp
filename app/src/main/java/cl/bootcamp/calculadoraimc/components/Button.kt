package cl.bootcamp.calculadoraimc.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalcButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFB388FF), // Color de fondo del botón
            contentColor = Color.White // Color del texto del botón
        )
    ) {
        Text("Calcular IMC")
    }

}

@Composable
fun ConfirmButton(onClick: () -> Unit, buttonText: String = "Comprendido") {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFB388FF),  // Color de fondo
            contentColor = Color.White       // Color del texto
        )
    ) {
        Text(buttonText)
    }
}


@Composable
fun DismissButton(onClick: () -> Unit) {
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFB388FF),
            contentColor = Color.White
        )
    ) {
        Text("Cancelar")
    }
}

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit,  // Primer parámetro: función onClick
    containerColor: Color = Color(0xFFB388FF),  // Segundo parámetro: color de fondo
    contentColor: Color = Color.White           // Tercer parámetro: color del ícono
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,  // Ícono predeterminado
            contentDescription = "Agregar",
            modifier = Modifier.size(24.dp)
        )
    }
}
// Botón personalizado llamado CardButton
@Composable
fun  CardButton(onClick: () -> Unit){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFB388FF), // Color de fondo del botón
            contentColor = Color.White // Color del texto del botón
        )
    ) {
        Text("Calcular IMC")
    }

}
@Composable
fun ConfirmButtonAPD(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFB388FF), // Color de fondo del botón
            contentColor = Color.White    // Color del texto del botón
        )
    ) {
        Text("Agregar")
    }
}