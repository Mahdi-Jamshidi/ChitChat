package com.example.chitchat.model.repository.auth

interface AuthRepository {
    suspend fun signIn(mobile: String, password: String): String

    fun loadToken()
    fun saveToken(newToken: String)
    fun getToken(): String?

}