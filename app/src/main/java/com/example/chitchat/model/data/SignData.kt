package com.example.chitchat.model.data

data class SignData(
    val code: String,
    val message: String,
    val ok: Boolean,
    val result: Result
) {
    data class Result(
        val token: String,
        val user: User
    ) {
        data class User(
            val code: String,
            val created_at: String,
            val creator_id: Int,
            val deleted_at: Any,
            val device: String,
            val email: Any,
            val email_verified_at: Any,
            val id: Int,
            val ip: String,
            val mobile: Any,
            val name: String,
            val permission_flag: Any,
            val permissions: Permissions,
            val role: String,
            val status: String,
            val updated_at: String,
            val user_type: String,
            val username: String
        ) {
            data class Permissions(
                val food: Food,
                val meal: Meal,
                val order: Order,
                val restaurant: Restaurant,
                val setting: Setting,
                val user: User
            ) {
                data class Food(
                    val create: Boolean,
                    val delete: Boolean,
                    val read: Boolean,
                    val update: Boolean
                )

                data class Meal(
                    val create: Boolean,
                    val delete: Boolean,
                    val read: Boolean,
                    val update: Boolean
                )

                data class Order(
                    val create: Boolean,
                    val delete: Boolean,
                    val read: Boolean,
                    val update: Boolean
                )

                data class Restaurant(
                    val create: Boolean,
                    val delete: Boolean,
                    val read: Boolean,
                    val update: Boolean
                )

                data class Setting(
                    val create: Boolean,
                    val delete: Boolean,
                    val read: Boolean,
                    val update: Boolean
                )

                data class User(
                    val create: Boolean,
                    val delete: Boolean,
                    val read: Boolean,
                    val update: Boolean
                )
            }
        }
    }
}