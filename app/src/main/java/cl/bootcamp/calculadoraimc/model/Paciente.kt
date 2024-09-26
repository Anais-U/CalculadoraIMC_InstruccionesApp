package cl.bootcamp.calculadoraimc.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pacientes")
data class Paciente(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0, // El ID será generado automáticamente
  val nombre: String,
  val edad:String = "",
  val altura: String = "",
  val peso: String = "",
  val imc: String = "",
  val sexo: String = "",
  val estadoSalud: String = "",
  var imcCalculado: Boolean = false // Para saber si ya se ha calculado el imc

)
