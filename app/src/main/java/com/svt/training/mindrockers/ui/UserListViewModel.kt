package com.svt.training.mindrockers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.svt.training.mindrockers.api.ApiResponse
import com.svt.training.mindrockers.api.User
import com.svt.training.mindrockers.repository.UserRepository
import kotlinx.coroutines.*
import java.lang.Exception

class UserListViewModel(private val userRepository: UserRepository): ViewModel() {

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _userList = MutableLiveData<ApiResponse<List<User>>>()

    val userList : LiveData<ApiResponse<List<User>>>
        get() = _userList

    init {
        uiScope.launch {
            getUserList()
        }
    }

    private suspend fun getUserList() = withContext(Dispatchers.IO){
        _userList.postValue(ApiResponse.loading(data = null))
        try {
            _userList.postValue(ApiResponse.success(data = userRepository.getUsers()))
        }catch (exception : Exception){
            _userList.postValue(ApiResponse.error(exception.message, data = userRepository.getUsers()))
        }
    }
}