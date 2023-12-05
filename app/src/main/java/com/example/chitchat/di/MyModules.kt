package com.example.chitchat.di

import android.content.Context
import com.example.chitchat.model.net.createApiService
import com.example.chitchat.model.repository.auth.AuthRepository
import com.example.chitchat.model.repository.auth.AuthRepositoryImpl
import com.example.chitchat.model.repository.message.MessageRepository
import com.example.chitchat.model.repository.message.MessageRepositoryImpl
import com.example.chitchat.ui.features.chatDialogs.DialogsViewModel
import com.example.chitchat.ui.features.signIn.SignInViewModel
import com.example.chitchat.ui.features.signUp.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val myModules = module {

    single { createApiService() }
    single { androidContext().getSharedPreferences("userConfig", Context.MODE_PRIVATE) }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<MessageRepository> { MessageRepositoryImpl(get()) }

    viewModel { SignUpViewModel() }
    viewModel { SignInViewModel(get()) }
    viewModel { DialogsViewModel(get()) }
}