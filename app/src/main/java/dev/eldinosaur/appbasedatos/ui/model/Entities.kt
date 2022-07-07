package dev.eldinosaur.appbasedatos.ui.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tblPet")
data class Pet (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "nombre")
    val nombre:String,
    @ColumnInfo(name = "raza")
    val raza:String,
    @ColumnInfo(name = "preferencias")
    val preferencias:String
        ):Serializable