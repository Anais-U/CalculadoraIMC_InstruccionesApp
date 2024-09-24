package cl.bootcamp.calculadoraimc.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.bootcamp.calculadoraimc.model.Paciente

@Dao
interface PacienteDao {
    @Query("SELECT * FROM pacientes")  // La consulta funciona con la tabla 'pacientes'
    suspend fun obtenerPacientes(): List<Paciente>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarPaciente(paciente: Paciente)

    @Delete
    suspend fun eliminarPaciente(paciente: Paciente)
}
