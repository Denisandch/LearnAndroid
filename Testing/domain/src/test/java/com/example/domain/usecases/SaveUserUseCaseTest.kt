package com.example.domain.usecases

import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class SaveUserUseCaseTest {

    val userRepository = mock<UserRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun shouldNotSaveDataIfUserExist() {

        val useCase = SaveUserUseCase(repository = userRepository)

        val testData = User("Denis", "Samolovov")

        Mockito.`when`(userRepository.getUser()).thenReturn(testData)
        val actual = useCase.saveUser(testData)
        val expected = false

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun shouldNotCallSaveIfExist() {
        val useCase = SaveUserUseCase(repository = userRepository)

        val testData = User("Denis", "Samolovov")

        Mockito.`when`(userRepository.getUser()).thenReturn(testData)
        useCase.saveUser(testData)

        Mockito.verify(userRepository, Mockito.never()).saveUser(user = any())
    }

    @Test
    fun mustToSaveUserIfNotExist() {
        val useCase = SaveUserUseCase(repository = userRepository)

        val testDataExist = User("", "")
        val testDataNew = User("Denis", "Samolovov")

        Mockito.`when`(userRepository.getUser()).thenReturn(testDataExist)
        Mockito.`when`(userRepository.saveUser(testDataNew)).thenReturn(true)

        val actual = useCase.saveUser(testDataNew)
        val expected = true

        Assert.assertEquals(expected, actual)
    }
}