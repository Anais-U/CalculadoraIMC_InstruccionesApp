package cl.bootcamp.calculadoraimc.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import cl.bootcamp.calculadoraimc.model.Paciente

@Database(entities = [Paciente::class], version = 2)
abstract class PacienteDatabase : RoomDatabase() {

    abstract fun pacienteDao(): PacienteDao

    companion object {
        @Volatile
        private var INSTANCE: PacienteDatabase? = null


        // Definir migraciones
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Aquí puedes definir los cambios en la base de datos, por ejemplo, agregar una columna
                // database.execSQL("ALTER TABLE Paciente ADD COLUMN nuevoCampo TEXT")
            }
        }

        fun getDatabase(context: Context): PacienteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PacienteDatabase::class.java,
                    "paciente_database"
                )
                    // Agregar la migración aquí
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}