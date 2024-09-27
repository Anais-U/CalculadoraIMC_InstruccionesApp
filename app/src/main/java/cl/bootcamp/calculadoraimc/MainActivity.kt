package cl.bootcamp.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import cl.bootcamp.calculadoraimc.navigation.NavigationComponent
import cl.bootcamp.calculadoraimc.ui.theme.CalculadoraImcTheme
import cl.bootcamp.calculadoraimc.view.SetStatusBarColor
import cl.bootcamp.calculadoraimc.datastore.DataStoreManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar pantalla completa (Edge-to-Edge)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Instanciar DataStoreManager para gestionar si el Onboarding ya fue completado
        val dataStoreManager = DataStoreManager(this)

        setContent {
            // Aplicar el tema
            CalculadoraImcTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Crear NavHostController con rememberNavController
                    val navController = rememberNavController()

                    // Llamar al componente de navegación y pasar el navController y dataStoreManager
                    NavigationComponent(
                        navController = navController,
                        dataStoreManager = dataStoreManager
                    )

                    // Cambiar el color de la barra de estado
                    SetStatusBarColor(Color(0xFFB388FF))  // Color morado para la barra de estado
                }
            }
        }
    }
}
