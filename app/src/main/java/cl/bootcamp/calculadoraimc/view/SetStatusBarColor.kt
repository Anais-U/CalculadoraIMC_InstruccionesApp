package cl.bootcamp.calculadoraimc.view

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SetStatusBarColor(color: Color) {
    val window = (LocalContext.current as? ComponentActivity)?.window ?: return
    val view = LocalView.current

    SideEffect {
        // Cambiar el color de la barra de estado
        window.statusBarColor = color.toArgb()

        // Obtener el controlador de insets de la ventana
        val insetsController = WindowInsetsControllerCompat(window, view)

        // Establecer si los iconos de la barra de estado deben ser claros u oscuros
        insetsController.isAppearanceLightStatusBars = color.luminance() > 0.5
    }
}
