package com.example.domain.usecases

import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class TestRepository : UserRepository {
    override fun saveUser(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun getUser(): User {
        return User("Denis", "Samolovov")
    }

}

class GetUserUseCaseTest {

    @Test
    fun shouldReturnCorrectData() {

        val testRepository = TestRepository()
        val useCase = GetUserUseCase(repository = testRepository)

        val actual = useCase.getUser()
        val expected = User("Denis", "Samolovov")

        Assert.assertEquals(expected, actual)
    }

    val userRepository = mock<UserRepository>()

    @Test   //with mockito
    fun shouldReturnCorrectDataMock() {

        val testData = User("Denis", "Samolovov")
        Mockito.`when`(userRepository.getUser()).thenReturn(testData)

        val useCase = GetUserUseCase(repository = userRepository)
        val actual = useCase.getUser()
        val expected = User("Denis", "Samolovov")

        Assert.assertEquals(expected, actual)
    }
}