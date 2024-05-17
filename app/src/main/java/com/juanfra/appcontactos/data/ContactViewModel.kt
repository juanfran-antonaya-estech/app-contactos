package com.juanfra.appcontactos.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.juanfra.appcontactos.data.model.Contacto
import kotlinx.coroutines.launch

class ContactViewModel(val context: Context) : ViewModel() {

    val repo = Repositorio(context)

    var contactosLiveData = repo.obtenerContactos()
    val contactoActual = MutableLiveData<Contacto?>()
    val nameLiveData = MutableLiveData<String>()

    fun vaciarContacto(){
        contactoActual.value = null
    }

    fun buscarPorNombre(s : String){
        if (s.isNullOrEmpty()){
            contactosLiveData = repo.obtenerContactos()
        } else {
            contactosLiveData = repo.obtenerContactos(s)
        }
    }

    fun addContact(contacto: Contacto){
        viewModelScope.launch {
            repo.addContacto(contacto)
        }
    }
    fun updateContact(contacto: Contacto){
        viewModelScope.launch {
            repo.updateContacto(contacto)
        }
    }
    fun deleteContact(contacto: Contacto){
        viewModelScope.launch {
            repo.deleteContacto(contacto)
        }
    }
    fun borrarTodo(){
        viewModelScope.launch {
            repo.borrarTodo()
        }

    }

    class ContactViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Context::class.java).newInstance(context)
        }
    }

}