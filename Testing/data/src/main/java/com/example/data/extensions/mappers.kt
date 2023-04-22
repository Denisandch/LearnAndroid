package com.example.data.extensions

import com.example.data.model.UserData
import com.example.domain.model.User

fun UserData.toUserDomain(): User {
    return User(name = name, last_name = last_name)
}

fun User.toUserData(): UserData {
    return UserData(name = name, last_name = last_name)
}