package com.example.chitchat.model.repository

object TokenInMemory {
    var token: String? = null
        private set
    fun refreshMemoryToken(newToken: String?) {
        this.token = newToken
    }
}