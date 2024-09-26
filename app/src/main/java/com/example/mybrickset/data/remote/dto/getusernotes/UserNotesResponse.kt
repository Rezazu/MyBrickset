package com.example.mybrickset.data.remote.dto.getusernotes

data class UserNotesResponse(
    val matches: Int,
    val status: String,
    val userNotes: List<UserNote>
)