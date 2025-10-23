package com.example.green_ev2 //En que paquete de la app esta este archivo

//LIBRERIAS
import android.os.Bundle
import androidx.activity.enableEdgeToEdge //layout pueda llegar hasta los bordes de la pantalla
import androidx.appcompat.app.AppCompatActivity //la clase base para las Activities en Android
//Estos dos de abajo son para ajustar las vistas y no tapar las barras del sistema
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//IMPORTAR
import android.content.Intent //Sirve para moverse entre pantallas (activiy)
//Estos de abajo son para cuando se ejecute algo sea despues de unos segundos
import android.os.Handler
import android.os.Looper

//hereda de AppCompatActivity la base de cualquier pantalla en Android
class SplashActivity : AppCompatActivity() {
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //El layout llega a los bordes de la pantalla
        setContentView(R.layout.activity_splash) //Esto carga el XML que diseñe

        // Ajuste para no tapar barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Esto es para splach dure segundos
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java) //Aqui es la otra pantalla donde quiero ir, es decir mi Login
            startActivity(intent) //abre esa la pantalla
            finish() //cierra la ventana Splash para que uno no pueda volver a ella
        }, 4000)

    }
}