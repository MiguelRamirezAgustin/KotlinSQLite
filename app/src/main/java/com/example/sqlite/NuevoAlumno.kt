package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NuevoAlumno : AppCompatActivity() {

    var crud :AlumnoCrud? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_alumno)

        val id = findViewById<EditText>(R.id.etId)
        val nombre = findViewById<EditText>(R.id.etNombre)
        val bAdd = findViewById<Button>(R.id.bAdd)

        crud = AlumnoCrud(this)

        bAdd.setOnClickListener{
            crud?.newAlumno(Alumno(id.text.toString(), nombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
