package com.svt.training.mindrockers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.svt.training.mindrockers.repository.UserRepository

class UserListViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserListViewModel::class.java)){
            return UserListViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}