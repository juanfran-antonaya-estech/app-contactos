package com.juanfra.appcontactos.data

import android.content.Context
import com.juanfra.appcontactos.data.db.ContactDataBase
import com.juanfra.appcontactos.data.model.Contacto

class Repositorio(val context: Context) {

    val db = ContactDataBase.getDatabase(context)
    val cd = db.contactosDao()

    suspend fun addContacto(contacto: Contacto) = cd.addContacto(contacto)
    suspend fun updateContacto(contacto: Contacto) = cd.updateContacto(contacto)
    suspend fun deleteContacto(contacto: Contacto) = cd.deleteContacto(contacto)
    fun obtenerContactos() = cd.obtenerContactos()
    fun obtenerContactos(s: String) = cd.obtenerContactosPorNombre(s)
    suspend fun borrarTodo() = cd.borrarTodosContactos()
}