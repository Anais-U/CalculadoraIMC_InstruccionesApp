package cl.bootcamp.calculadoraimc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.bootcamp.calculadoraimc.datastore.DataStoreManager
import cl.bootcamp.calculadoraimc.onBoardingViews.MainOnBoarding
import cl.bootcamp.calculadoraimc.view.CalculadoraIMC
import cl.bootcamp.calculadoraimc.view.ListaPacientes
import cl.bootcamp.calculadoraimc.viewmodel.PacienteViewModel

@Composable
fun NavigationComponent(
    navController: NavHostController,
    dataStoreManager: DataStoreManager // DataStoreManager para verificar si se ha completado el Onboarding
) {
    // Leer el estado de si el Onboarding ya fue completado
    val onboardingCompleted by dataStoreManager.getBoarding.collectAsState(initial = false)

    // Decidir la pantalla inicial
    NavHost(navController = navController,
        startDestination = if (onboardingCompleted) "listaPacientes" else "onBoarding"
    ) {
        // Pantalla de Onboarding
        composable("onBoarding") {
            MainOnBoarding(modifier = Modifier, navController, dataStoreManager)
        }

        // Pantalla de la lista de pacientes
        composable("listaPacientes") {
            val viewModel: PacienteViewModel = viewModel()
            ListaPacientes(navController = navController, viewModel = viewModel)
        }

        // Pantalla de la calculadora de IMC
        composable("calculadoraIMC/{nombrePaciente}") { backStackEntry ->
            val nombrePaciente = backStackEntry.arguments?.getString("nombrePaciente")
            CalculadoraIMC(nombrePaciente ?: "Paciente", navController)
        }
    }
}
