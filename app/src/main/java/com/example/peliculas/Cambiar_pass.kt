package com.example.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Cambiar_pass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_pass)


        var btnLogin = findViewById<Button>(R.id.passLogin);
        btnLogin.setOnClickListener{
            startActivity(Intent(this, Login::class.java))

        }

    }
}