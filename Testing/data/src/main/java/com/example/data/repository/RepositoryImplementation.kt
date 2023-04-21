package com.example.data.repository

import com.example.data.extensions.toUserNameData
import com.example.data.extensions.toUserNameDomain
import com.example.data.repository.storage.Storage
import com.example.domain.models.UserName
import com.example.domain.repository.Repository

class RepositoryImplementation(
    private val storage: Storage
): Repository {
    override fun getUserName(): UserName {
        return storage.getUserName().toUserNameDomain()
    }

    override fun saveUserName(user: UserName) {
       storage.saveUserName(user.toUserNameData())
    }
}