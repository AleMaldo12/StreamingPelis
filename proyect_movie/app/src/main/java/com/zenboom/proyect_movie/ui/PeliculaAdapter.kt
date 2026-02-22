package com.zenboom.proyect_movie.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zenboom.proyect_movie.R
import com.zenboom.proyect_movie.data.Pelicula

class PeliculaAdapter(private val peliculas: List<Pelicula>) :
    RecyclerView.Adapter<PeliculaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.txtTitulo)
        val descripcion: TextView = view.findViewById(R.id.txtDescripcion)
        val imagen: ImageView = view.findViewById(R.id.imgPoster)
        val btnVer: Button = view.findViewById(R.id.btnVer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = peliculas[position]
        holder.titulo.text = "${pelicula.titulo} - ${pelicula.genero}"
        holder.descripcion.text = pelicula.descripcion

        Glide.with(holder.itemView.context)
            .load(pelicula.imagenUrl)
            .into(holder.imagen)

        holder.btnVer.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Reproduciendo: ${pelicula.titulo}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = peliculas.size
}