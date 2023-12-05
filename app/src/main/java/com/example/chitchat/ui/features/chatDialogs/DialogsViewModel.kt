package com.example.chitchat.ui.features.chatDialogs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chitchat.model.data.DialogsResponse
import com.example.chitchat.model.repository.message.MessageRepository
import kotlinx.coroutines.launch

class DialogsViewModel(
    private val messageRepository: MessageRepository
):ViewModel() {
    val searchValue = MutableLiveData("")
    var dialogs by mutableStateOf(listOf<DialogsResponse.Data.Chat>())

    fun getDialogs(){
        viewModelScope.launch {
            val allDialogs = arrayListOf<DialogsResponse.Data.Chat>()
            dialogs = messageRepository.getDialogs().data.chats
//            messageRepository.getDialogs().data.chats.forEach {
//                allDialogs.addAll()
//            }

        }
    }

}