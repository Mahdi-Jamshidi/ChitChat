package com.example.chitchat.ui.features.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel() : ViewModel() {

    val name = MutableLiveData("")
    val mobile = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
    val errors = MutableLiveData(ArrayList<Int>())
    val playLoadingAnim = MutableLiveData(false)

    fun signUpUsers() {

    }
}