package com.example.green_ev2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Para cmabiar de ventana
import android.widget.TextView


class crear_cuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_cuenta)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonRegistrar = findViewById<Button>(R.id.boton_registro)

        botonRegistrar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Registro exitoso")
                .setMessage("¡Tu cuenta ha sido creada correctamente!")
                .setPositiveButton("Aceptar") { dialog, _ ->
                    dialog.dismiss()  // Cierra la alerta
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent) // Va al login
                    finish() // Cierra la pantalla de crear cuenta
                }
                .show()
        }
    }
}