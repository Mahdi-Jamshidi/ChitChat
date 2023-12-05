package com.example.chitchat.model.repository.message

import com.example.chitchat.model.data.DialogsResponse

interface MessageRepository {

    suspend fun getDialogs(): DialogsResponse
}