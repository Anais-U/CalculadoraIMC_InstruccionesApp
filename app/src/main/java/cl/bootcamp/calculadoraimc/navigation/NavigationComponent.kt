package cl.bootcamp.calculadoraimc.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.bootcamp.calculadoraimc.view.CalculadoraIMC
import cl.bootcamp.calculadoraimc.view.ListaPacientes
import cl.bootcamp.calculadoraimc.viewmodel.PacienteViewModel


@Composable
fun NavigationComponent(navController: NavHostController) {
    // Configura las rutas de la aplicación
    NavHost(navController = navController, startDestination = "listaPacientes") {
        // Pantalla de la lista de pacientes
        composable("listaPacientes") {
            val viewModel: PacienteViewModel = viewModel()  // Usar el ViewModel aquí también
            ListaPacientes(navController = navController, viewModel = viewModel)
        }

        // Pantalla de la calculadora de IMC
        composable("calculadoraIMC/{nombrePaciente}") { backStackEntry ->
            val nombrePaciente = backStackEntry.arguments?.getString("nombrePaciente")
            CalculadoraIMC(nombrePaciente ?: "Paciente", navController)
        }
    }
}
