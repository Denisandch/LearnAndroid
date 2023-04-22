package com.example.data.repository

import com.example.data.extensions.toUserData
import com.example.data.extensions.toUserDomain
import com.example.data.repository.storage.UserStorage
import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class UserRepositoryImplementation(
    private val storage: UserStorage
) : UserRepository {
    override fun saveUser(user: User): Boolean {
        storage.saveUser(user.toUserData())
        return true
    }

    override fun getUser(): User {
        return storage.getUser().toUserDomain()
    }
}