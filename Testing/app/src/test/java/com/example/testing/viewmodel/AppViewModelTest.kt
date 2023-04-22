package com.example.testing.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.User
import com.example.domain.usecases.GetUserUseCase
import com.example.domain.usecases.SaveUserUseCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.kotlin.mock

class AppViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val getUserUseCase = mock<GetUserUseCase>()
    val saveUserUseCase = mock<SaveUserUseCase>()

    @AfterEach
    fun afterEach() {
        Mockito.reset(getUserUseCase)
        Mockito.reset(saveUserUseCase)
    }

    @BeforeEach
    fun beforeEach() {

    }

    @Test
    fun shouldSaveUserTrue() {
        val user = User("Denis", "Samolovov")
        Mockito.`when`(saveUserUseCase.saveUser(user)).thenReturn(true)

        val viewModel = AppViewModel(
            getUserUseCase = getUserUseCase,
            saveUserUseCase = saveUserUseCase
        )

        viewModel.save(user)

        val expected = "SAVED"
        val actual = viewModel.result.value

        Mockito.verify(saveUserUseCase, Mockito.times(1)).saveUser(user)
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun shouldGetUser() {
        val user = User("Denis", "Samolovov")

        Mockito.`when`(getUserUseCase.getUser()).thenReturn(user)

        val viewModel = AppViewModel(
            getUserUseCase = getUserUseCase,
            saveUserUseCase = saveUserUseCase
        )

        viewModel.load()

        val actual = viewModel.result.value
        val expected = "Denis, Samolovov"

        Mockito.verify(getUserUseCase, Mockito.times(1)).getUser()
        Assert.assertEquals(expected, actual)
    }
}