package com.example.chitchat.di

import com.example.chitchat.ui.features.signUp.SignUpViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val myModules = module {

    viewModel {  SignUpViewModel() }
}