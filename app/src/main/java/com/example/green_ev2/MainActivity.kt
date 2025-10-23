package com.example.green_ev2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Para boton
import androidx.appcompat.app.AlertDialog
import android.widget.Button

//Para cmabiar de ventana
import android.widget.TextView
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Esto dice: trae a mi código el botón que está en la pantalla, lo busca por el id que yo nombre
        val boton = findViewById<Button>(R.id.boton_ingresar)

        //Cuando hagan click en el botón
        boton.setOnClickListener {
            //Va crear una ventana emergente
            AlertDialog.Builder(this)
                //Texto que va aparecer
                .setTitle("Ingreso exitoso")
                .setMessage("¡Bienvenida de nuevo!")
                //Va tener un boton dentro
                .setPositiveButton("Aceptar") { dialog, _ ->
                    dialog.dismiss() //Esto va cerrar la alerta
                }
                .show() //Va a mostrar la alerta
        }

        //Esto va en este main del login PORQUE ahi estan los link de recuperar
        //Esto es para redicionar
        val link_Ventana = findViewById<TextView>(R.id.link_recuperar)

        link_Ventana.setOnClickListener {
            val intent = Intent(this, recuperar_Cuenta::class.java)
            startActivity(intent)
        }

        //Lo mismo aqui, para redicionar
        val link2_Ventana = findViewById<TextView>(R.id.link_crearCuenta)

        link2_Ventana.setOnClickListener {
            val intent = Intent(this, crear_cuenta::class.java)
            startActivity(intent)
        }




    }
}