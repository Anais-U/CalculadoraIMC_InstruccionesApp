package cl.bootcamp.calculadoraimc.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import cl.bootcamp.calculadoraimc.components.CalcButton
import cl.bootcamp.calculadoraimc.components.ConfirmButton
import cl.bootcamp.calculadoraimc.components.ConfirmButtonCalc
import cl.bootcamp.calculadoraimc.components.InputField
import cl.bootcamp.calculadoraimc.components.MultiButtonSegmented
import cl.bootcamp.calculadoraimc.viewmodel.PacienteViewModel

@Composable
fun CalculadoraIMC(nombrePaciente: String,
    navController: NavHostController,
    viewModel: PacienteViewModel = viewModel()
) {

    // Busca el paciente por su nombre
    val paciente = viewModel.listaPacientes.value.find { it.nombre == nombrePaciente }

    if (paciente != null) {
        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Calculadora de IMC para $nombrePaciente",
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // MultiButton Segmentado para el género
        MultiButtonSegmented(
            options = listOf("Hombre", "Mujer"),
            selectedOption = viewModel.gender.value,
            onOptionSelected = { viewModel.updateGender(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input para Edad
        InputField(
            label = "Edad",
            value = viewModel.age.value,
            onValueChange = { viewModel.updateAge(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input para Altura
        InputField(
            label = "Altura (cm)",
            value = viewModel.height.value,
            onValueChange = { viewModel.updateHeight(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input para Peso
        InputField(
            label = "Peso (kg)",
            value = viewModel.weight.value,
            onValueChange = { viewModel.updateWeight(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para calcular IMC
        CalcButton(onClick = { viewModel.calcularIMC(paciente)
        })

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar el IMC y el estado de salud si ha sido calculado
        if (paciente.imcCalculado) {
            Text(text = paciente.imc)
            Text(text = "Estado de salud:${paciente.estadoSalud}")

            Spacer(modifier = Modifier.height(16.dp))


                ConfirmButtonCalc(onClick = {
                    // Actualizar el paciente con los datos calculados
                    val pacienteActualizado = paciente.copy(
                        edad = viewModel.age.value,
                        sexo = viewModel.gender.value,


                    )
                    viewModel.actualizarPaciente(pacienteActualizado)
                    navController.navigate("listaPacientes")
                })

            }
        }

                // Mostrar advertencia si los datos son incorrectos
                if (viewModel.showWarning.value) {
                    AlertDialog(
                        onDismissRequest = { viewModel.dismissWarning() },
                        confirmButton = {
                            ConfirmButton(onClick = { viewModel.dismissWarning() })
                        },
                        title = {
                            Text(
                                text = "¡Cuidado!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        },
                        text = {
                            Text(
                                text = "No olvides llenar todos los campos con datos correctos.",
                                fontSize = 18.sp


                            )
                        }
                    )
                }
            }
        }





