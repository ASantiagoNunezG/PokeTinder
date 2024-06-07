package com.nunez.abraham.poketinder

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel (
    val context: Context
):ViewModel(){
    //Inizializacion de repositorio shared preferences
    // also se usa aquí para ejecutar el método setSharedPreferences justo después de la creación del objeto
    private  val sharedPreferencesRepository = SharedPreferencesRepository().also {
        it.setSharedPreferences(context)
    }
    // LiveData para manejar errores de entrada (por ejemplo, campos vacíos)
    val inputsError = MutableLiveData<Boolean>()

    // LiveData para indicar el éxito del login
    val registerSuccess = MutableLiveData<Boolean>()

    // LiveData para manejar errores de autenticación (por ejemplo, usuario o contraseña incorrectos)
    val registerError = MutableLiveData<Boolean>()

    //agragando un error si la contraseña no esta bien definida
    val emailError = MutableLiveData<Boolean>()

    //Vamos a establecer los valores en shared preferences Nuñez
    fun validateInputs(email: String,password: String,password2: String){
        if (isEmptyInputs(email,password,password2)){
            inputsError.postValue(true)
            return
        }

        //CODIGO MAS ATRACTIVO
        // Verifica si el correo electrónico no es correcto y actualiza el LiveData en consecuencia
        if (isEmailCorrect(email) == false){
            emailError.postValue(true)
        }

        //no sirvió
        //emailError.postValue(!isEmailCorrect(email))

        // Verifica si las contraseñas no son iguales y actualiza el LiveData en consecuencia
        if (equalPasswords(password,password2)== false){
            registerError.postValue(true)
        }

        //no sirve, investigar otra manera
        //registerError.postValue(!equalPasswords(password, password2))

        if (equalPasswords(password,password2)&& isEmailCorrect(email)){
            sharedPreferencesRepository.saveUserEmail(email)
            sharedPreferencesRepository.saveUserPassword(password)
            registerSuccess.postValue(true)
        }
    }
    fun equalPasswords(password: String,password2: String) : Boolean{
        return password2 == password
    }
    //funcion para la validacion del correcto formato del correo
    fun isEmailCorrect(email: String): Boolean{
        return email.contains("@") && email.contains(".")
    }
    // Función para verificar si los campos de entrada están vacíos
    fun isEmptyInputs(email: String, password: String, password2: String) = email.isEmpty() || password.isEmpty() || password2.isEmpty()
}