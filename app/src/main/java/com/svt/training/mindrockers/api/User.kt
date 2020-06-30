package com.svt.training.mindrockers.api

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val userId: String,
    @SerializedName("name")
    val userName: String,
    @SerializedName("email")
    val emailId: String
)