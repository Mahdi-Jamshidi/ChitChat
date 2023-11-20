package com.example.chitchat.model.net

import com.example.chitchat.model.data.SignInData
import com.example.chitchat.model.repository.TokenInMemory
import com.example.chitchat.utils.BASE_URL
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun signIn(@Body jsonObject: JsonObject): SignInData
}

fun createApiService(): ApiService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val oldRequest = it.request()
            val newRequest = oldRequest.newBuilder()
            if (TokenInMemory.token != null)
                newRequest.header("Authorization", TokenInMemory.token)

            newRequest.method(oldRequest.method(), oldRequest.body())

            return@addInterceptor it.proceed(newRequest.build())
        }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}
