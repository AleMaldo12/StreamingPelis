package com.zenboom.proyect_movie.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zenboom.proyect_movie.R
import com.zenboom.proyect_movie.data.LoginRequest
import com.zenboom.proyect_movie.data.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        btnIngresar.setOnClickListener {
            val user = etUsuario.text.toString()
            val pass = etContrasena.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                // Llamamos a la API
                lifecycleScope.launch {
                    try {
                        val response = RetrofitClient.instance.hacerLogin(LoginRequest(user, pass))
                        if (response.isSuccessful) {
                            // Login correcto, pasamos a MainActivity
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@LoginActivity, "Error conectando al servidor local", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}