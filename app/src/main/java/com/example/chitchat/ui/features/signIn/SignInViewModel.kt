package com.example.chitchat.ui.features.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chitchat.model.repository.auth.AuthRepository
import com.example.chitchat.utils.coroutineExceptionHandler
import kotlinx.coroutines.launch

class SignInViewModel(private val authRepository: AuthRepository) : ViewModel() {
    val mobile = MutableLiveData("")
    val password = MutableLiveData("")
    val errors = MutableLiveData(ArrayList<Int>())
    val loggingMessage = MutableLiveData("")
    val playLoadingAnim = MutableLiveData(false)

    fun signInUser(LoggingEvent: (String) -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val mobileNumber = "+98${mobile.value!!}"
            val result = authRepository.signIn(mobileNumber, password.value!!)
            LoggingEvent(result)
        }
    }
}