package com.example.green_ev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AgregarNoticiaActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_noticia)

        val titulo = findViewById<EditText>(R.id.txtTitulo)
        val resumen = findViewById<EditText>(R.id.txtResumen)
        val contenido = findViewById<EditText>(R.id.txtContenido)
        // <-- AÑADIR: Vincula el nuevo EditText para la URL de la imagen
        val imagenUrl = findViewById<EditText>(R.id.txtImagenUrl)
        val autor = findViewById<EditText>(R.id.txtAutor)
        val fecha = findViewById<EditText>(R.id.txtFecha)

        val botonGuardar = findViewById<Button>(R.id.btnGuardar)

        botonGuardar.setOnClickListener {

            val data = hashMapOf(
                "titulo" to titulo.text.toString(),
                "resumen" to resumen.text.toString(),
                "contenido" to contenido.text.toString(),
                "imagenUrl" to imagenUrl.text.toString(),
                "autor" to autor.text.toString(),
                "fecha" to fecha.text.toString()
            )

            if (titulo.text.isNotBlank() && resumen.text.isNotBlank() && contenido.text.isNotBlank() && imagenUrl.text.isNotBlank() && autor.text.isNotBlank()) {
                db.collection("noticias")
                    .add(data)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Noticia guardada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }
    }
}
