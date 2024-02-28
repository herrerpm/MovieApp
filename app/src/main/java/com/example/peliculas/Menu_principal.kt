package com.example.peliculas

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Menu_principal : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    val database = Firebase.database;

    lateinit var peliculas:ArrayList<Pelicula>

    val myRef = database.getReference("Peliculas");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

//        var btnLogin = findViewById<Button>(R.id.menuLogin);
//        var texto = findViewById<TextView>(R.id.text_menu_principal);
//        texto.text = intent.getStringExtra("nombre")

//        btnLogin.setOnClickListener{
//            startActivity(Intent(this, Menu_principal::class.java));
//        }
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val menuHost: MenuHost = this;
        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.salir->{
                        auth.signOut()
                        startActivity(Intent(this@Menu_principal, Login::class.java))
                        finish()
                        true
                    }
                    R.id.perfil->{
                        true
                    }
                    else -> false
                }
            }
        })

        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                peliculas = ArrayList<Pelicula>()
                val value = snapshot.value
                Log.d(TAG, "Value is: " + value)
                snapshot.children.forEach{ hijo ->
                    var pelicula: Pelicula = Pelicula(
                        hijo.child("nombre").value.toString(),
                        hijo.child("genero").value.toString(),
                        hijo.child("año").value.toString(),
                        hijo.key.toString(),
                        )
                    peliculas.add(pelicula)
                }
                llenaLista()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })

    }

    fun llenaLista(){
        val adaptador = PeliAdaptador(this, peliculas)
        var lista = findViewById<ListView>(R.id.listview)
        lista.adapter = adaptador
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            auth.signOut()
        }
        return super.onKeyDown(keyCode, event)
    }
}