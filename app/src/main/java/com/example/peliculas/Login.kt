package com.example.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
// ...
// Initialize Firebase Auth

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            Toast.makeText(this, "Estas autenticado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth

        var btnCuenta = findViewById<Button>(R.id.login_cuenta);
        var btnMenu = findViewById<Button>(R.id.login_principal);
        var btnPassword = findViewById<Button>(R.id.login_password);

        btnCuenta.setOnClickListener{
            startActivity(Intent(this, Crear_cuenta::class.java))
        }
        btnMenu.setOnClickListener{
            auth.signInWithEmailAndPassword("root@root.com", "rootroot").addOnCompleteListener{
                task ->
                if(task.isSuccessful){
                    startActivity(Intent(this, Menu_principal::class.java).putExtra("nombre", "Juan"))
                }
                else{
                    Toast.makeText(this, "Error: "+task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnPassword.setOnClickListener{
            startActivity(Intent(this, Cambiar_pass::class.java))

        }


    }
}