package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    //variable
    var lista: RecyclerView? = null
    var adaptador:AdaptadorCustom? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    var alumnos:ArrayList<Alumno>? = null
    var crud : AlumnoCrud? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fab = findViewById<FloatingActionButton>(R.id.fab)
        lista = findViewById(R.id.lista)


        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        fab.setOnClickListener{
            startActivity(Intent(this, NuevoAlumno::class.java))
        }

        crud = AlumnoCrud (this)


        alumnos = crud?.getAlumnos()

        if( alumnos !=  null){
         Toast.makeText(this,
             "Lista de alumnos",
             Toast.LENGTH_SHORT
         ).show()
        }else{
            Toast.makeText(this,
                "No hay alumnos",
                Toast.LENGTH_SHORT
            ).show()
        }

        adaptador = AdaptadorCustom( alumnos!! ,object : ClickListener {
            override fun onClick(vista: View, index: Int) {
                //click
            }
        }, object : LongClickListener{
            override fun LongClick(vista: View, index: Int) {}
        })

        lista?.adapter = adaptador
    }
}
