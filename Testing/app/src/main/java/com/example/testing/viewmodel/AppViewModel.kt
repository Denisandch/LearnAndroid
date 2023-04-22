package com.example.testing.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetUserUseCase
import com.example.domain.usecases.SaveUserUseCase

class AppViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

}