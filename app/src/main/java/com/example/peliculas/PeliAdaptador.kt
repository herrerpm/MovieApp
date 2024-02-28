package com.example.peliculas

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat


class PeliAdaptador (private val contex : Activity, private val arrayList: ArrayList<Pelicula>)
    : ArrayAdapter<Pelicula>(contex, R.layout.item, arrayList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(contex)
        val view:View = inflater.inflate(R.layout.item, null)
        view.findViewById<TextView>(R.id.nombre).text = arrayList[position].nombre.toString()
        view.findViewById<TextView>(R.id.genero).text = arrayList[position].genero.toString()
        view.findViewById<TextView>(R.id.año).text = arrayList[position].ani.toString()

        if(arrayList[position].genero == "terror"){
            view.findViewById<ImageView>(R.id.imagen).setImageDrawable(ContextCompat.getDrawable(contex, R.drawable.terror))
        }

        return view
    }
}