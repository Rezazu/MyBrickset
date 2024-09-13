package com.example.mybrickset.data.remote.dto.login

data class LoginResponse(
    val hash: String,
    val status: String,
    val message: String?,
)