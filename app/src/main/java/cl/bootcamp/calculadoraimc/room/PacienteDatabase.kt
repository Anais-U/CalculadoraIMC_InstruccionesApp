package cl.bootcamp.calculadoraimc.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.bootcamp.calculadoraimc.model.Paciente

@Database(entities = [Paciente::class], version = 1)
abstract class PacienteDatabase : RoomDatabase() {

    abstract fun pacienteDao(): PacienteDao

    companion object {
        @Volatile
        private var INSTANCE: PacienteDatabase? = null

        fun getDatabase(context: Context): PacienteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PacienteDatabase::class.java,
                    "paciente_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
