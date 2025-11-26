package com.example.green_ev2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class home_ListadoNoticia : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private val listaNoticias = mutableListOf<Noticia>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_listado_noticia)

        recycler = findViewById(R.id.recyclerNoticias)
        recycler.layoutManager = LinearLayoutManager(this)

        // Botón Agregar Noticia
        val btnAgregar = findViewById<Button>(R.id.btnAgregarNoticia)
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarNoticiaActivity::class.java)
            startActivity(intent)
        }

        // Botón Actualizar
        val btnActualizar = findViewById<Button>(R.id.btnActualizar)
        btnActualizar.setOnClickListener {
            cargarNoticias()
        }

        // Botón Cerrar sesión
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        cargarNoticias()
    }

    private fun cargarNoticias() {
        db.collection("noticias")
            .get()
            .addOnSuccessListener { result ->
                listaNoticias.clear()
                for (doc in result) {
                    val noticia = doc.toObject(Noticia::class.java)
                    listaNoticias.add(noticia)
                }

                runOnUiThread {
                    recycler.adapter = NoticiaAdapter(listaNoticias) { noticia ->
                        val intent = Intent(this, VerNoticiaActivity::class.java)
                        intent.putExtra("titulo", noticia.titulo)
                        intent.putExtra("resumen", noticia.resumen)
                        intent.putExtra("contenido", noticia.contenido)
                        intent.putExtra("autor", noticia.autor)
                        intent.putExtra("fecha", noticia.fecha)
                        startActivity(intent)
                    }
                }
            }
            .addOnFailureListener {
                println("ERROR FIRESTORE: ${it.message}")
            }
    }
}
