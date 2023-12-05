package com.example.chitchat.model.repository.auth

import android.content.SharedPreferences
import com.example.chitchat.model.net.ApiService
import com.example.chitchat.model.repository.TokenInMemory
import com.example.chitchat.utils.VALUE_SUCCESS
import com.google.gson.JsonObject

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val sharePref: SharedPreferences
) : AuthRepository {
    override suspend fun signIn(mobile: String, password: String): String {
        val jsonObject = JsonObject().apply {
            addProperty("mobile", mobile)
            addProperty("password", password)
        }

        val result = apiService.signIn(jsonObject)
        if (result.status || result.code == 200) {
            val token = "Bearer " + result.data.token
            TokenInMemory.refreshMemoryToken(token)
            saveToken(token)
            return VALUE_SUCCESS
        } else {
            return result.message
        }
    }

    override fun loadToken() {
        // load token in SharedPreferences to memory cash
        TokenInMemory.refreshMemoryToken(getToken())
    }

    override fun saveToken(newToken: String) {
        sharePref.edit().putString("token", newToken).apply()
    }

    override fun getToken(): String? {
        return sharePref.getString("token", null)
    }
}