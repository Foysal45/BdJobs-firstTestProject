package com.example.testapplication.api.model

import com.google.gson.annotations.SerializedName

data class JobListModel(
    @SerializedName("common")
    val common: Common,
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("statuscode")
    val statuscode: String = ""
)