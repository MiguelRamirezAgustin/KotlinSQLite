package com.example.sqlite

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AlumnoCrud(context: Context) {

    private var helper:DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun newAlumno(item:Alumno){
        //--1. Abrir la base de datros de modo lectura
        val db: SQLiteDatabase = helper?.writableDatabase!!

        //---2. Mapeo de las columnos con valores  a insertar
        val values = ContentValues()
        values.put(AlumnosContact.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnosContact.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)

        //---3. Insertar una nueva fila en la tabla
        val newRowId = db.insert(AlumnosContact.Companion.Entrada.NOMBRE_TABLA, null, values)
        db.close()
    }


    fun getAlumnos():ArrayList<Alumno> {
        val item: ArrayList<Alumno> = ArrayList()

        //abrir en modo lectura
        val db:SQLiteDatabase = helper?.readableDatabase!!
        //espesificar las columnos a consultar
        val comunas = arrayOf(AlumnosContact.Companion.Entrada.COLUMNA_ID,
                        AlumnosContact.Companion.Entrada.COLUMNA_NOMBRE)

        //crear cursos para recorrer l atabla  deficion de query
        val c:Cursor = db.query(
            AlumnosContact.Companion.Entrada.NOMBRE_TABLA,
            comunas,
            null,
            null,
            null,
            null,
            null
        )

        //hacer el recorido del cursos en la tabla
            while(c.moveToNext()){
                item.add(
                    Alumno(
                        c.getString(c.getColumnIndexOrThrow(AlumnosContact.Companion.Entrada.COLUMNA_ID)),
                        c.getString(c.getColumnIndexOrThrow(AlumnosContact.Companion.Entrada.COLUMNA_NOMBRE))
                ))
            }
        db.close()
        return item
    }

    //obtener alumno por un id
    fun getAlumno(id:String):Alumno{
        var item:Alumno? = null

        val db:SQLiteDatabase = helper?.readableDatabase!!

        val columna = arrayOf(AlumnosContact.Companion.Entrada.COLUMNA_ID,
                               AlumnosContact.Companion.Entrada.COLUMNA_NOMBRE)

        var c:Cursor = db.query(
            AlumnosContact.Companion.Entrada.NOMBRE_TABLA,
            columna,
            " id = ?",
            arrayOf(id),
            null,
            null,
            null
        )
        while (c.moveToNext()){
            item = Alumno(c.getString(c.getColumnIndexOrThrow(AlumnosContact.Companion.Entrada.COLUMNA_ID)),
                          c.getString(c.getColumnIndexOrThrow(AlumnosContact.Companion.Entrada.COLUMNA_NOMBRE)))

        }
        c.close()
        return  item!!
    }


    //Actualizar un alumno
    fun updateAlumno(item:Alumno){
        //abrir coneccion
         val db:SQLiteDatabase = helper?.writableDatabase!!

        //mapear los datos con contenValues
        val values = ContentValues()
        values.put(AlumnosContact.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnosContact.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)

        db.update(
             AlumnosContact.Companion.Entrada.NOMBRE_TABLA,
             values,
            "id = ?",
             arrayOf(item.id))
        db.close()
    }

    //Eliminar Alumno
    fun deleteAlumno(item: Alumno){
        val db:SQLiteDatabase = helper?.writableDatabase!!

        db.delete(AlumnosContact.Companion.Entrada.NOMBRE_TABLA,
            "id = ?",
            arrayOf(item.id))
        db.close()
    }



}