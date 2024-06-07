package com.nunez.abraham.poketinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import com.nunez.abraham.poketinder.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = LoginViewModel(this)
        observeValues()
    }

    private fun observeValues() {
        loginViewModel.inputsError.observe(this) {
            Toast.makeText(this, "Ingrese los datos completos", Toast.LENGTH_SHORT).show()
        }
        loginViewModel.authError.observe(this) {
            Toast.makeText(this, "Error usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
        loginViewModel.loginSuccess.observe(this) {
            Toast.makeText(this, "¡Bienvenido!",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        
        binding.btnLogin.setOnClickListener {
            loginViewModel.validateInputs(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString()
            )
        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
