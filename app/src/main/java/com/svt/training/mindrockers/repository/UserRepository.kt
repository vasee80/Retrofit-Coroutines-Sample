package com.svt.training.mindrockers.repository

import com.svt.training.mindrockers.api.ApiService


class UserRepository(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()
}