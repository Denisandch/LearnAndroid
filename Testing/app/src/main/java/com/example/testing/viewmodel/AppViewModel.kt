package com.example.testing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.User
import com.example.domain.usecases.GetUserUseCase
import com.example.domain.usecases.SaveUserUseCase

class AppViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    fun save(user: User) {
        saveUserUseCase.saveUser(user)
        _result.value = "SAVED"
    }

    fun load() {
        val user = getUserUseCase.getUser()
        _result.value = "${user.name}, ${user.last_name}"
    }
}