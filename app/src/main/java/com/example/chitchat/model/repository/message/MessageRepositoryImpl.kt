package com.example.chitchat.model.repository.message

import com.example.chitchat.model.data.DialogsResponse
import com.example.chitchat.model.net.ApiService

class MessageRepositoryImpl(
    private val apiService: ApiService
) : MessageRepository {
    override suspend fun getDialogs(): DialogsResponse {

        val response = apiService.getAllDialogs()
        return response
    }
}