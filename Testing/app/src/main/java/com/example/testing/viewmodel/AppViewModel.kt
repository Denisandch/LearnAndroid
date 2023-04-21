package com.example.testing.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.models.UserName
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.SaveUserUseCase

class AppViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val f = saveUserUseCase.saveUser(UserName("1","2"))
    val r = getUserUseCase.getUser()
}