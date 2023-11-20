package com.example.chitchat.utils

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->

    Log.v("error", "Coroutine error-> " + throwable.message)
}