package com.example.testapplication.api.model


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("Message")
    var message: String? = ""
)