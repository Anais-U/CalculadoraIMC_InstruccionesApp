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
    var estadoSalud = mutableStateOf("")  // Estado de salud del paciente
    var showWarning = mutableStateOf(false)
    var mostrarGuardar = mutableStateOf(false)

    init {
        obtenerPacientes() // Cargar los pacientes al inicio
    }

    // Método para obtener los pacientes desde la base de datos
    private fun obtenerPacientes() {
        viewModelScope.launch {
            listaPacientes.value = pacienteDao.obtenerPacientes()
        }
    }

    // Método para agregar un nuevo paciente
    fun agregarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            // Verifica si el paciente ya existe
            val pacienteExistente = listaPacientes.value.find { it.nombre == paciente.nombre }

            if (pacienteExistente == null) {
                pacienteDao.agregarPaciente(paciente)
                obtenerPacientes() // Actualiza la lista después de agregar
            }
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
    fun calcularIMC(paciente: Paciente) {
        val peso = weight.value.toDoubleOrNull()
        val alturaEnMetros = height.value.toDoubleOrNull()?.div(100) // Convertir cm a metros
        if (peso != null && alturaEnMetros != null && alturaEnMetros > 0) {
            val imc = peso / (alturaEnMetros * alturaEnMetros)
            // Determinar el estado de salud según el IMC


            val estadoSalud = when {
                imc < 18.5 -> "Bajo peso"
                imc in 18.5..24.9 -> "Peso normal"
                imc in 25.0..29.9 -> "Sobrepeso"
                imc in 30.0..34.9 -> "Obesidad I"
                imc in 35.0..39.9 -> "Obesidad II"
                else -> "Obesidad III"
            }

            // Actualiza el paciente con el nuevo IMC y estado de salud
            val pacienteActualizado = paciente.copy(
                imc = "IMC: %.2f".format(imc),
                estadoSalud = estadoSalud,
                imcCalculado = true  // Marcar como calculado
            )

            // Actualiza la lista de pacientes reemplazando el paciente antiguo
            actualizarPaciente(pacienteActualizado)
        } else {
            showWarning.value = true  // Mostrar advertencia si los datos son incorrectos
        }
        mostrarGuardar.value = true

    }



    // Método para actualizar un paciente después de calcular su IMC
    fun actualizarPaciente(pacienteActualizado: Paciente) {
        viewModelScope.launch {
            val nuevaLista = listaPacientes.value.toMutableList()
            val indice = nuevaLista.indexOfFirst { it.id == pacienteActualizado.id }

            if (indice != -1) {
                // Reemplaza el paciente existente con los datos actualizados
                nuevaLista[indice] = pacienteActualizado
                listaPacientes.value = nuevaLista

            }

            // Actualiza la base de datos
            pacienteDao.agregarPaciente(pacienteActualizado)
        }
    }


    // Método para ocultar la advertencia
        fun dismissWarning() {
            showWarning.value = false
        }
    }
    
