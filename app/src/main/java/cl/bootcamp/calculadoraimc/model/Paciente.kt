package cl.bootcamp.calculadoraimc.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pacientes")
data class Paciente(
  @PrimaryKey(autoGenerate = true) val id: Int = 0, // El ID será generado automáticamente
  val nombre: String,
  val edad:String = "",
  val altura: String = "",
  val peso: String = ""
)
