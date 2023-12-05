package com.example.chitchat.model.data

data class DialogsResponse(
    val code: Int,
    val `data`: Data,
    val message: String,
    val status: Boolean
) {
    data class Data(
        val chatIds: List<Int>,
        val chats: List<Chat>,
        val currentChatDifference: List<Any>,
        val currentChatIds: List<Any>
    ) {
        data class Chat(
            val avatar_address: String,
            val created_at: String,
            val creator_id: Any,
            val deleted_at: Any,
            val id: Int,
            val messages_count: Int,
            val title: String,
            val type: String,
            val updated_at: String,
            val user_id: Int,
            val user_name: String
        )
    }
}