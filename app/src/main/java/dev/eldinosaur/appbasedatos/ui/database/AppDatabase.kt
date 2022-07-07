package dev.eldinosaur.appbasedatos.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.eldinosaur.appbasedatos.ui.model.Pet


@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    //Definir los Dao a utilizar
    abstract fun petDao():PetDao

    //Defiir la instancia de la base de datos
    companion object{
        var instancia:AppDatabase? =null

        //Manejar la instanca de la base de datos
        fun getInstancia(context: Context):AppDatabase{
            if (instancia ==  null){
                instancia = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "bdPets"
                ).build()
            }
            return instancia as AppDatabase
        }
    }
}