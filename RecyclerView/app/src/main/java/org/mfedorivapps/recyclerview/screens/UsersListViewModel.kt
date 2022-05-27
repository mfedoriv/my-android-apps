package org.mfedorivapps.recyclerview.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mfedorivapps.recyclerview.model.User
import org.mfedorivapps.recyclerview.model.UsersListener
import org.mfedorivapps.recyclerview.model.UsersService

class UsersListViewModel(
    private val usersService: UsersService
) : ViewModel(){

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val listener: UsersListener = {
        _users.value = it
    }

    init {
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        // because UsersService is singleton -> has bigger lifecycle than ViewModel -> prevent memory leak
        usersService.removeListener(listener)
    }

    fun loadUsers() {
        usersService.addListener(listener)
    }

    fun moveUser(user: User, moveBy: Int) {
        usersService.moveUser(user, moveBy)
    }

    fun deleteUser(user: User) {
        usersService.deleteUser(user)
    }
}