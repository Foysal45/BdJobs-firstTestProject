package com.example.testapplication.api.model

import com.google.gson.annotations.SerializedName

data class Common(
    @SerializedName("total_records_found")
    val total_records_found: Int = 0,
    @SerializedName("totalpages")
    val totalpages: Int = 0
)