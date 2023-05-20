package com.example.mvvmrepeat

import com.example.mvvmrepeat.model.User

interface Navigator {

    fun showDetails(user: User)

    fun goBack()

    fun toast(messageRes: Int)
}