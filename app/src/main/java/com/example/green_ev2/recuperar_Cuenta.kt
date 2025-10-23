package com.example.green_ev2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Pra boton
import androidx.appcompat.app.AlertDialog
import android.widget.Button

//Para cmabiar de ventana
import android.widget.TextView
import android.content.Intent

class recuperar_Cuenta : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperar_cuenta)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //boton_recuperar
        val botonRecuperar = findViewById<Button>(R.id.boton_recuperar)

        botonRecuperar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Se ha enviado el codigo")
                .setMessage("¡Revisa tu bandeja de entrada!")
                .setPositiveButton("Aceptar") { dialog, _ ->
                    dialog.dismiss()  // Cierra la alerta
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .show()
        }



    }
}