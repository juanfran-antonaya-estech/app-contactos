package com.juanfra.appcontactos.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juanfra.appcontactos.data.model.Contacto

@Database(entities = [Contacto::class], version = 1)
abstract class ContactDataBase : RoomDatabase() {

    abstract fun contactosDao(): ContactosDao

    companion object {
        const val DB_NAME = "contactos_db"

        @Volatile
        private var INSTANCE: ContactDataBase? = null

        fun getDatabase(context: Context) : ContactDataBase {
            val temporalInstance = INSTANCE
            if (temporalInstance != null)
                return temporalInstance

            synchronized(ContactDataBase::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDataBase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }

}