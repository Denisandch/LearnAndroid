package com.example.mvvmrepeat.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmrepeat.R
import com.example.mvvmrepeat.UserActionListener
import com.example.mvvmrepeat.model.User
import com.example.mvvmrepeat.model.UsersListener
import com.example.mvvmrepeat.model.UsersService
import com.example.mvvmrepeat.tasks.EmptyResult
import com.example.mvvmrepeat.tasks.ErrorResult
import com.example.mvvmrepeat.tasks.PendingResult
import com.example.mvvmrepeat.tasks.Result
import com.example.mvvmrepeat.tasks.SuccessResult

data class UserListItem(
    val user: User,
    val isInProgress: Boolean
)

class UsersListViewModel(
    private val usersService: UsersService
) : BaseViewModel(), UserActionListener {

    private val _users = MutableLiveData<Result<List<UserListItem>>>()
    val users: LiveData<Result<List<UserListItem>>> = _users

    private val _actionShowDetails = MutableLiveData<Event<User>>()
    val actionShowDetails: LiveData<Event<User>> = _actionShowDetails

    private val _actionShowToast = MutableLiveData<Event<Int>>()
    val actionShowToast: LiveData<Event<Int>> = _actionShowToast

    private val userIdsInProgress = mutableSetOf<Long>()
    private var userResult: Result<List<User>> = EmptyResult()
        set(value) {
            field = value
            notifyUpdates()
        }

    private val usersListener: UsersListener = {
        userResult = if (it.isEmpty()) {
            EmptyResult()
        } else {
            SuccessResult(it)
        }
    }

    init {
        usersService.addListener(usersListener)
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        usersService.deleteListener(usersListener)
    }

    private fun loadUsers() {
        userResult = PendingResult()
        usersService.loadUsers()
            .onError {
                userResult = ErrorResult(it)
            }
            .autoCancel()
    }


    private fun addProgressTo(user: User) {
        userIdsInProgress.add(user.id)
        notifyUpdates()
    }

    private fun removeProgressFrom(user: User) {
        userIdsInProgress.remove(user.id)
        notifyUpdates()
    }

    private fun isInProgress(user: User): Boolean {
        return userIdsInProgress.contains(user.id)
    }

    private fun notifyUpdates() {
        _users.postValue(userResult.map { users ->
            users.map { user -> UserListItem(user, isInProgress(user)) }
        })
    }

    override fun onUserMove(user: User, moveBy: Int) {
        if (isInProgress(user)) return
        addProgressTo(user)
        usersService.moveUser(user, moveBy)
            .onSuccess {
                removeProgressFrom(user)
            }
            .onError {
                removeProgressFrom(user)
                _actionShowToast.value = Event(R.string.cant_move_user)
            }
            .autoCancel()
    }

    override fun onUserDelete(user: User) {
        if (isInProgress(user)) return
        addProgressTo(user)
        usersService.deleteUser(user)
            .onSuccess {
                removeProgressFrom(user)
            }
            .onError {
                removeProgressFrom(user)
                _actionShowToast.value = Event(R.string.cant_delete_user)
            }
            .autoCancel()
    }

    override fun onUserDetails(user: User) {
        _actionShowDetails.value = Event(user)
    }

    override fun onUserFire(user: User) {
        usersService.fireUser(user)
    }

}