package com.juanfra.appcontactos.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactos")
data class Contacto(
    var nombre : String,
    var apellidos : String,
    var telefono: Int,
    var email: String,
    var edad: Int,
    var fav: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0;
}
