package com.juanfra.appcontactos.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.juanfra.appcontactos.data.model.Contacto

@Dao
interface ContactosDao {

    @Insert
    suspend fun addContacto(contacto: Contacto)

    @Update
    suspend fun updateContacto(contacto: Contacto)

    @Delete
    suspend fun deleteContacto(contacto: Contacto)

    @Query("SELECT * FROM contactos")
    fun obtenerContactos() : LiveData<List<Contacto>>

    @Query("SELECT * FROM contactos WHERE nombre LIKE '%' || :name || '%'")
    fun obtenerContactosPorNombre(name: String) : LiveData<List<Contacto>>

    @Query("DELETE FROM contactos WHERE 1")
    suspend fun borrarTodosContactos()

}