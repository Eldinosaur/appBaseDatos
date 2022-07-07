package dev.eldinosaur.appbasedatos.ui.database

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.eldinosaur.appbasedatos.ui.model.Pet


@Dao
interface PetDao {
    //Se define lo que se va a hacer
    //CRUD
    @Insert
    fun insert(pet:Pet):Long

    @Update
    fun update(pet:Pet)

    @Delete
    fun delete(pet:Pet)

    @Query("select * from tblPet order by id desc")
    fun getPets():LiveData<List<Pet>>

    @Query("select * from tblPet where id=:idInput")
    fun getPetById(idInput:Int):List<Pet>


}