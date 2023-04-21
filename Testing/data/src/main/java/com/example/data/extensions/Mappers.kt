package com.example.data.extensions

import com.example.data.models.UserNameData
import com.example.domain.models.UserName

fun UserNameData.toUserNameDomain(): UserName {
    return UserName(
        firstName = firstName,
        lastName = lastName
    )
}

fun UserName.toUserNameData(): UserNameData {
    return UserNameData(
        firstName = firstName,
        lastName = lastName
    )
}