package com.example.chitchat.model.data

data class SignInData(
    val code: Int,
    val `data`: Data,
    val message: String,
    val status: Boolean
) {
    data class Data(
        val token: String,
        val user: User
    ) {
        data class User(
            val code_expire_at: Any,
            val created_at: String,
            val deleted_at: Any,
            val device: Any,
            val email: String,
            val id: Int,
            val ip: Any,
            val last_active_at: Any,
            val mobile: String,
            val mobile_verified_at: String,
            val name: String,
            val role: String,
            val status: String,
            val updated_at: String,
            val verification_code: Any
        )
    }
}