package com.nunez.abraham.poketinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.nunez.abraham.poketinder.databinding.ActivityRegisterBinding
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {

    // Declaración de la propiedad ViewModel como lateinit
    private lateinit var  binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Nuñez no te olvides de inflar el activity
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        //Esto estamos configurando el activity con binding.
        setContentView(binding.root)
        //En este caso, this es una referencia a LoginActivity, que es un tipo de Context
        // Inicialización del ViewModel
        registerViewModel = RegisterViewModel(this)
        observeValues()

    }

    //Aqui toy' trabajando con los componetes de la UI
    private fun observeValues(){
        registerViewModel.inputsError.observe(this){
            Toast.makeText(this, "Ingrese los datos completos", Toast.LENGTH_SHORT).show()
        }
        registerViewModel.emailError.observe(this){
            Toast.makeText(this,"Ingrese un correo Válido.", Toast.LENGTH_SHORT).show()
        }
        registerViewModel.registerError.observe(this){
            Toast.makeText(this, "Error en las contraseñas, recuerde que deben ser iguales", Toast.LENGTH_SHORT).show()
        }
        registerViewModel.registerSuccess.observe(this){
            Toast.makeText(this, "¡Registrado correctamente!, continue con sus credenciales", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener{
            registerViewModel.validateInputs(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString(),
                password2 = binding.edtPassword2.text.toString()
            )
        }

        //Con esto inicializando el Login, bueno la navegación, al presionar el botón
        binding.btnLoginF.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}