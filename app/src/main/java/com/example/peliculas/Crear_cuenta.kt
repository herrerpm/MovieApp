package com.example.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Crear_cuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)

        var btnLogin = findViewById<Button>(R.id.cuentaLogin);
        btnLogin.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }

    }
}