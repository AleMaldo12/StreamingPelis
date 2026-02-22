package com.zenboom.proyect_movie.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zenboom.proyect_movie.R
import com.zenboom.proyect_movie.data.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvPeliculas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Traemos las películas desde tu API en IntelliJ
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.obtenerPeliculas()
                if (response.isSuccessful && response.body() != null) {
                    val peliculas = response.body()!!
                    recyclerView.adapter = PeliculaAdapter(peliculas)
                } else {
                    Toast.makeText(this@MainActivity, "Error al cargar catálogo", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "No se pudo conectar con la API", Toast.LENGTH_LONG).show()
            }
        }
    }
}