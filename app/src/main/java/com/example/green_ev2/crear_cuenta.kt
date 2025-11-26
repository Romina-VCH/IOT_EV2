package com.example.green_ev2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class crear_cuenta : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)

        auth = FirebaseAuth.getInstance()

        val etNombre = findViewById<EditText>(R.id.texto_registro)
        val etPassword = findViewById<EditText>(R.id.contra_recuperar)
        val btnRegistrar = findViewById<Button>(R.id.boton_registro)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nombre.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Ingresa nombre y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registrarUsuario(nombre, password)
        }
    }

    private fun registrarUsuario(nombre: String, password: String) {
        // Para este ejemplo usaremos el nombre como email ficticio
        val email = "$nombre@example.com" // si quieres pedir correo en layout, cambia esto

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Guardar el nombre en Firestore
                    val usuario = hashMapOf(
                        "nombre" to nombre,
                        "uid" to auth.currentUser?.uid
                    )

                    db.collection("usuarios")
                        .document(auth.currentUser!!.uid)
                        .set(usuario)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show()
                            finish() // vuelve al login
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error guardando usuario: ${e.message}", Toast.LENGTH_SHORT).show()
                        }

                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
