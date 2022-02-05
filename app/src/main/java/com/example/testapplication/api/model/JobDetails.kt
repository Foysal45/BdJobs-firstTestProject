package com.example.testapplication.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobDetails(
    @SerializedName("ApplyInstruction")
    val ApplyInstruction: String = "",
    @SerializedName("CompanyName")
    val CompanyName: String = "",
    @SerializedName("LastDate")
    val LastDate: String = "",
    @SerializedName("Title")
    val Title: String = ""
) : Parcelable