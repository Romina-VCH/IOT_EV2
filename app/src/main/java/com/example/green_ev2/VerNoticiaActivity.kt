package com.example.green_ev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class VerNoticiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_noticia)

        val titulo = findViewById<TextView>(R.id.txtTituloDetalle)
        val contenido = findViewById<TextView>(R.id.txtContenido)
        val autor = findViewById<TextView>(R.id.txtAutor)
        val fecha = findViewById<TextView>(R.id.txtFecha)




        // Leer los extras que enviamos
        titulo.text = intent.getStringExtra("titulo")
        contenido.text = intent.getStringExtra("contenido")
        autor.text = intent.getStringExtra("autor")
        fecha.text = intent.getStringExtra("fecha")
    }
}


