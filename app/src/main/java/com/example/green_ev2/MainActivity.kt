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

//Importar para usar firebase google
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

// Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider




class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val RC_SIGN_IN = 123


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Esto dice: trae a mi código el botón que está en la pantalla, lo busca por el id que yo nombre
        val boton = findViewById<Button>(R.id.boton_ingresar)

        //Cuando hagan click en el botón
        boton.setOnClickListener {
            val email = findViewById<TextView>(R.id.texto_nombre).text.toString().trim()
            val pass = findViewById<TextView>(R.id.texto_contraseña).text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Debes ingresar correo y contraseña")
                    .setPositiveButton("Aceptar", null)
                    .show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, home_ListadoNoticia::class.java))
                        finish()
                    } else {
                        AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Credenciales incorrectas o usuario no existe")
                            .setPositiveButton("Aceptar", null)
                            .show()
                    }
                }
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

        //BOTON GOOGLE - este es un segundo boton en login para inicar seccion
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        // 2. Botón de Google
        val botonGoogle = findViewById<Button>(R.id.boton_google)
        botonGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("No se pudo iniciar sesión: ${e.message}")
                    .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, home_ListadoNoticia::class.java))
                    finish()
                } else {
                    AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("No se pudo autenticar el usuario")
                        .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
                        .show()
                }
            }
    }


}