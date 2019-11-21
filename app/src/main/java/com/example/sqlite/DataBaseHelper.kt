package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context): SQLiteOpenHelper(context , AlumnosContact.Companion.Entrada.NOMBRE_TABLA, null, AlumnosContact.VERSION) {

    companion object{
        val CREATE_ALUMNO_TABLE = "CREATE TABLE " + AlumnosContact.Companion.Entrada.NOMBRE_TABLA  +
                " (" + AlumnosContact.Companion.Entrada.COLUMNA_ID + " TEXT PRIMARY KEY, " +
                   AlumnosContact.Companion.Entrada.COLUMNA_NOMBRE + " TEXT )"

        val REMOVE_ALUMNOS_TABLE =  "DROP TABLE IF EXISTS" + AlumnosContact.Companion.Entrada.NOMBRE_TABLA
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //query para crear la tabla
        db?.execSQL(CREATE_ALUMNO_TABLE)
     }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, i2: Int) {
        //se ejecuta cada vez que se actualiza la base de datos
        db?.execSQL(REMOVE_ALUMNOS_TABLE)
        onCreate(db)
    }

}