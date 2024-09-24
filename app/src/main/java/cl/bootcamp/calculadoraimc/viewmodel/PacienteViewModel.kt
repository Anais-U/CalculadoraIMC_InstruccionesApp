package cl.bootcamp.calculadoraimc.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.calculadoraimc.model.Paciente
import cl.bootcamp.calculadoraimc.room.PacienteDatabase
import kotlinx.coroutines.launch



class PacienteViewModel(application: Application) : AndroidViewModel(application) {

    private val pacienteDao = PacienteDatabase.getDatabase(application).pacienteDao()

    // Lista de pacientes
    var listaPacientes = mutableStateOf<List<Paciente>>(emptyList())
        private set

    // Propiedades para la calculadora de IMC
    var gender = mutableStateOf("Hombre")
    var age = mutableStateOf("")
    var height = mutableStateOf("")
    var weight = mutableStateOf("")
    var imcResult = mutableStateOf("")
    var showWarning = mutableStateOf(false)

    init {
        obtenerPacientes() // Cargar los pacientes al inicio
    }

    // Método para obtener los pacientes desde la base de datos
    fun obtenerPacientes() {
        viewModelScope.launch {
            listaPacientes.value = pacienteDao.obtenerPacientes()
        }
    }

    // Método para agregar un nuevo paciente
    fun agregarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            pacienteDao.agregarPaciente(paciente)
            obtenerPacientes() // Actualiza la lista después de agregar
        }
    }

    // Método para eliminar un paciente
    fun eliminarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            pacienteDao.eliminarPaciente(paciente)
            obtenerPacientes() // Actualiza la lista después de eliminar
        }
    }

    // Métodos para la calculadora de IMC

    // Actualizar el género
    fun updateGender(newGender: String) {
        gender.value = newGender
    }

    // Actualizar la edad
    fun updateAge(newAge: String) {
        age.value = newAge
    }

    // Actualizar la altura
    fun updateHeight(newHeight: String) {
        height.value = newHeight
    }

    // Actualizar el peso
    fun updateWeight(newWeight: String) {
        weight.value = newWeight
    }

    // Calcular el IMC
    fun calcularIMC() {
        val peso = weight.value.toDoubleOrNull()
        val alturaEnMetros = height.value.toDoubleOrNull()?.div(100) // Convertir cm a metros
        if (peso != null && alturaEnMetros != null && alturaEnMetros > 0) {
            val imc = peso / (alturaEnMetros * alturaEnMetros)
            imcResult.value = "IMC: %.2f".format(imc)

            // Ocultar advertencia cuando los datos son correctos
            showWarning.value = false
        } else {
            // Mostrar advertencia si los datos son incorrectos
            showWarning.value = true
        }
    }

    // Método para ocultar la advertencia
    fun dismissWarning() {
        showWarning.value = false
    }
}
