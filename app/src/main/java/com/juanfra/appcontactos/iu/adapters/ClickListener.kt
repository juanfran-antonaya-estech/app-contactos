package com.juanfra.appcontactos.iu.adapters

import com.juanfra.appcontactos.data.model.Contacto

interface ClickListener {
    fun onClick(contacto: Contacto)
}