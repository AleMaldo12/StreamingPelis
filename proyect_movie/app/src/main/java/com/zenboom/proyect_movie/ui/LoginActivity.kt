package com.zenboom.proyect_movie.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zenboom.proyect_movie.ui.MainActivity
import com.zenboom.proyect_movie.data.LoginRequest
import com.zenboom.proyect_movie.data.RetrofitClient
import com.zenboom.proyect_movie.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            val userEmail = binding.etUsuario.text.toString().trim()
            val userPass = binding.etContrasena.text.toString().trim()

            if (userEmail.isNotEmpty() && userPass.isNotEmpty()) {
                realizarLogin(userEmail, userPass)
            } else {
                Toast.makeText(this, "Por favor, completa los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun realizarLogin(email: String, pass: String) {
        val request = LoginRequest(email = email, password = pass)

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.hacerLogin(request)

                if (response.isSuccessful && response.body() != null) {
                    // Éxito: El usuario existe en RDS
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    // El servidor respondió pero los datos no coinciden
                    Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Fallo de red (URL incorrecta o sin internet)
                Log.e("API_ERROR", "Error: ${e.message}")
                Toast.makeText(this@LoginActivity, "Error de conexión con AWS", Toast.LENGTH_LONG).show()
            }
        }
    }
}