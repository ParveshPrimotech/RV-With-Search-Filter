package com.example.myapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.emailValidations
import com.example.myapp.nameValidations
import com.example.myapp.passwordValidations
import com.example.myapp.repository.MainRepository

class SignupViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val validationMsg : MutableLiveData<Int> = MutableLiveData()

    fun setSignupData(name: String, email: String, password: String) {
       val nameVal = name.nameValidations()
       val emailVal = email.emailValidations()
       val passwordVal = password.passwordValidations()

        when{
            nameVal != null -> {
                validationMsg.postValue(nameVal)
            }
            emailVal != null -> {
                validationMsg.postValue(emailVal)
            }
            passwordVal != null -> {
                validationMsg.postValue(passwordVal)
            }
            else -> {
                // Hit api
            }
        }
    }
}